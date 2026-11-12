package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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
            }
        } catch (NumberFormatException e) {

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }
}
