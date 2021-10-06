package com.crosskey.mortgagecalculator.service;

import com.crosskey.mortgagecalculator.domain.ProspectsEntity;
import com.crosskey.mortgagecalculator.exceptions.MortgageCalculationException;
import com.crosskey.mortgagecalculator.repository.ProspectRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculateMortgageServiceTest {


    private CalculateMortgageService calculateMortgageService;

    @Mock
    private ProspectRepository prospectRepository;

    @Captor
    ArgumentCaptor<List<ProspectsEntity>> prospectsEntityArgumentCaptor;


    @BeforeAll
    void cleanUp() {
        calculateMortgageService = new CalculateMortgageService(prospectRepository);
    }

    @Test
    void shouldCheckMortgageForCustomer() {
       String file = "classpath:customerdetails/prospects.txt";
       calculateMortgageService.calculateMortgageAndSave(file);
       Mockito.verify(prospectRepository).saveAll(prospectsEntityArgumentCaptor.capture());
       List<ProspectsEntity> prospectsEntities = prospectsEntityArgumentCaptor.getValue();
       String prospect = prospectsEntities.get(0).getProspect();
       assertThat(prospect).isEqualTo("Juha wants to borrow 1000€ for a period of 2 years and pay 43.87€ each month");
       assertEquals(4, prospectsEntities.size());
    }

    @Test
    @DisplayName("Should throw an exception when wrong file path is specified")
    void shouldThrowExceptionWhenFileNotFound() {
        assertThrows(MortgageCalculationException.class,
                () -> calculateMortgageService.calculateMortgageAndSave("wrongFile"));
    }

}
