package com.pluralsight.dealershipmanager.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LoanCalculator {

    private LoanCalculator () {
    }

    private static BigDecimal monthlyPayment(BigDecimal loanAmount, double yearlyRatePercent, int months) {
        double monthlyRate = yearlyRatePercent / 100 / 12;
        double payment = loanAmount.doubleValue() * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months));

        return BigDecimal.valueOf(payment).setScale(2, RoundingMode.HALF_UP);
    }
}
