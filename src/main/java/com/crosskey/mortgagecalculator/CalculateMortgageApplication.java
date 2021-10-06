package com.crosskey.mortgagecalculator;

import com.crosskey.mortgagecalculator.service.CalculateMortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculateMortgageApplication implements CommandLineRunner {

	@Autowired
	private CalculateMortgageService calculateMortgageService;

	@Value("${customer-details.file:classpath:customerdetails/prospects.txt}")
	private String customerDetailsFile;

	public static void main(String[] args) {
		SpringApplication.run(CalculateMortgageApplication.class, args);
	}

	@Override
	public void run(String[] args)  {
		calculateMortgageService.calculateMortgageAndSave(customerDetailsFile);
	}
}
