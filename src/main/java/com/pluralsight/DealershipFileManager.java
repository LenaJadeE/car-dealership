package com.pluralsight;

import java.io.*;
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
        } catch (IOException e) {
            System.out.println("Could not read " + FILE_NAME + ".");
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(FILE_NAME));

            bufWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                String line = vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|"
                        + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|"
                        + vehicle.getOdometer() + "|" + String.format("%.2f", vehicle.getPrice());

                bufWriter.write(line + "\n");
            }

            bufWriter.close();
        } catch (IOException e) {
            System.out.println("Could not save " + FILE_NAME + ".");
        }
    }
}
