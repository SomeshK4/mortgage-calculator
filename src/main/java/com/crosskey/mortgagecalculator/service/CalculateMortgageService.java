package com.crosskey.mortgagecalculator.service;

import com.crosskey.mortgagecalculator.domain.ProspectsEntity;
import com.crosskey.mortgagecalculator.exceptions.MortgageCalculationException;
import com.crosskey.mortgagecalculator.model.CustomerRecord;
import com.crosskey.mortgagecalculator.repository.ProspectRepository;
import com.crosskey.mortgagecalculator.utils.CalculatePowerUtility;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class CalculateMortgageService {

    private static Logger log = LoggerFactory.getLogger(CalculateMortgageService.class);
    private static final Pattern pattern = Pattern.compile(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");

    private final ProspectRepository prospectRepository;

    @Autowired
    public CalculateMortgageService(ProspectRepository prospectRepository) {
        this.prospectRepository = prospectRepository;
    }

    @Transactional
    public void calculateMortgageAndSave(String customerDetailsFile)  {
        try {
            File file = ResourceUtils.getFile(customerDetailsFile);
            log.info("Loaded Customer records from file : {} ", file.getPath());

            try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
                List<ProspectsEntity> prospectsEntities = stream.skip(1)
                        .map(line -> pattern.split(line))
                        .map(customerRecord -> new CustomerRecord(customerRecord[0].replaceAll("\"", ""),
                                new BigDecimal(customerRecord[1]),
                                new BigDecimal(customerRecord[2]), Integer.parseInt(customerRecord[3])))
                        .map(customerRecord -> calculateMonthlyPayment(customerRecord))
                        .collect(Collectors.toList());
                prospectRepository.saveAll(prospectsEntities);
            }
        }catch (Exception exception){
            log.error("Error occurred while calculating mortgage for customers ", exception);
            throw new MortgageCalculationException("Error occurred while calculating mortgage for customers ", exception);
        }
    }

    /**
     * Method to calculate the mortgage for a customer using formula E = U[b(1 + b)^p]/[(1 + b)^p - 1]
     * where b = interestRate, U = totalLoan, p = numberOfPayments
     * @param customerRecord
     * @return prospect
     */
    private ProspectsEntity calculateMonthlyPayment(CustomerRecord customerRecord) {
        int time = customerRecord.getYears() * 12; //time is converted to months
        double rateOfInterest = (customerRecord.getInterest().doubleValue() / 100) / 12; //Interest converted to month format
        double payment = customerRecord.getTotalLoan().doubleValue() * ((rateOfInterest * CalculatePowerUtility.calculatePowerOfNumber(
                1 + rateOfInterest,
                time))
                / (CalculatePowerUtility.calculatePowerOfNumber(1 + rateOfInterest, time) - 1));
        ProspectsEntity prospectsEntity = new ProspectsEntity();

        String prospect = "%s wants to borrow %s€ for a period of %d years and pay %s€ each month";
        String result = String.format(prospect,
                customerRecord.getCustomerName(),
                customerRecord.getTotalLoan(),
                customerRecord.getYears(),
                BigDecimal.valueOf(payment).setScale(2,
                        RoundingMode.HALF_UP));
        prospectsEntity.setProspect(result);

        return prospectsEntity;
    }

}
