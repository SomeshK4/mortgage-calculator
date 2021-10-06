package com.crosskey.mortgagecalculator.model;

import java.math.BigDecimal;

public class CustomerRecord {

    private String customerName;
    private BigDecimal interest;
    private BigDecimal totalLoan;
    private int years;

    public CustomerRecord(String customerName, BigDecimal totalLoan, BigDecimal interest, int years) {
        this.customerName = customerName;
        this.totalLoan = totalLoan;
        this.interest = interest;
        this.years = years;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getTotalLoan() {
        return totalLoan;
    }

    public int getYears() {
        return years;
    }
}
