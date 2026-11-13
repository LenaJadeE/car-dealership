package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;


public class DealershipFileManager {
    private static final String FILE_NAME = "inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = new Dealership("Unknown Dealership", "", "");

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(FILE_NAME));

            String input = bufReader.readLine();
            if (input != null) {
                String[] dealershipFields = input.split("\\|");

                if (dealershipFields.length == 3) {
                    dealership = new Dealership(dealershipFields[0], dealershipFields[1], dealershipFields[2]);
                }
            }

            while ((input = bufReader.readLine()) != null) {
                String[] fields = input.split("\\|");

                if (fields.length != 8) {
                    continue;
                }

                try {
                    int vin = Integer.parseInt(fields[0]);
                    int year = Integer.parseInt(fields[1]);
                    String make = fields[2];
                    String model = fields[3];
                    String vehicleType = fields[4];
                    String color = fields[5];
                    int odometer = Integer.parseInt(fields[6]);
                    BigDecimal price = new BigDecimal(fields[7]);

                    dealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));

                } catch (NumberFormatException e) {
                    System.out.println("Skipped a vehicle line with a bad number: " + input);
                }
            }

            bufReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }
}
