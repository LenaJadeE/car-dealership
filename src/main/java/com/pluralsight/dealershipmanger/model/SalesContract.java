package com.pluralsight.dealershipmanger.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class SalesContract extends Contract {

    private BigDecimal salesTaxAmount;
    private BigDecimal recordingFee;
    private boolean financed;

    public SalesContract(LocalDate contractDate, String customerName, String customerEmail,
                         Vehicle vehicleSold, boolean financed) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        setSalesTaxAmount(vehicleSold.getPrice().multiply(new BigDecimal("0.05")).setScale(2, RoundingMode.HALF_UP));
        setRecordingFee(new BigDecimal("100.00"));
        setFinanced(financed);
    }

    public BigDecimal getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(BigDecimal salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public BigDecimal getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(BigDecimal recordingFee) {
        this.recordingFee = recordingFee;
    }

    public boolean isFinanced() {
        return financed;
    }

    public void setFinanced(boolean financed) {
        this.financed = financed;
    }

    public BigDecimal getProcessingFee() {
        if (getVehicleSold().getPrice().compareTo(new BigDecimal("10000")) < 0) {
            return new BigDecimal("295.00");
        }
        return new BigDecimal("495.00");
    }

    @Override
    public BigDecimal getTotalPrice() {
        return getVehicleSold().getPrice()
                .add(getSalesTaxAmount())
                .add(getRecordingFee())
                .add(getProcessingFee());
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        if (!isFinanced()) {
            return new BigDecimal("0.00");
        }

        if (getVehicleSold().getPrice().compareTo(new BigDecimal("10000")) >= 0) {
            return LoanCalculator.monthlyPayment(getTotalPrice(), 4.25, 48);
        }
        return LoanCalculator.monthlyPayment(getTotalPrice(), 5.25, 24);
    }
}
