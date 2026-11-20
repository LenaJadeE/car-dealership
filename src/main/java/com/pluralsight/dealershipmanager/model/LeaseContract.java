package com.pluralsight.dealershipmanager.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class LeaseContract extends Contract{

    private BigDecimal expectedEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        setExpectedEndingValue(vehicleSold.getPrice().multiply(new BigDecimal("0.50")).setScale(2, RoundingMode.HALF_UP));
        setLeaseFee(vehicleSold.getPrice().multiply(new BigDecimal("0.07")).setScale(2, RoundingMode.HALF_UP));
    }

    public BigDecimal getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(BigDecimal expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public BigDecimal getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(BigDecimal leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return getExpectedEndingValue().add(getLeaseFee());
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        return LoanCalculator.monthlyPayment(getTotalPrice(), 4.0, 36);
    }
}
