package com.pluralsight.dealershipmanger.data;

import com.pluralsight.dealershipmanger.model.Contract;
import com.pluralsight.dealershipmanger.model.LeaseContract;
import com.pluralsight.dealershipmanger.model.SalesContract;
import com.pluralsight.dealershipmanger.model.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ContractDataManager {
    private static final String FILE_NAME = "contracts.csv";

    public void saveContract(Contract contract) {
        String line = buildLine(contract);

        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bufWriter.write(line + "\n");
            bufWriter.close();
        } catch (IOException e) {
            System.out.println("Could not save " + FILE_NAME + ".");
        }
    }

    private String buildLine(Contract contract) {
        Vehicle vehicle = contract.getVehicleSold();
        String date = contract.getContractDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String start = date + "|" + contract.getCustomerName() + "|" + contract.getCustomerEmail() + "|"
                + vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|"
                + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|"
                + vehicle.getOdometer() + "|" + String.format("%.2f", vehicle.getPrice());
        String line = "";

        if (contract instanceof SalesContract sale) {
            line = "SALE|" + start + "|"
                    + String.format("%.2f", sale.getSalesTaxAmount()) + "|"
                    + String.format("%.2f", sale.getRecordingFee()) + "|"
                    + String.format("%.2f", sale.getProcessingFee()) + "|"
                    + String.format("%.2f", sale.getTotalPrice()) + "|"
                    + (sale.isFinanced() ? "YES" : "NO") + "|"
                    + String.format("%.2f", sale.getMonthlyPayment());
        } else if (contract instanceof LeaseContract lease) {
            line = "LEASE|" + start + "|"
                    + String.format("%.2f", lease.getExpectedEndingValue()) + "|"
                    + String.format("%.2f", lease.getLeaseFee()) + "|"
                    + String.format("%.2f", lease.getTotalPrice()) + "|"
                    + String.format("%.2f", lease.getMonthlyPayment());
        }

        return line;
    }
}
