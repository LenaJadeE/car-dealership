package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private DealershipFileManager fileManager;
    private final Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        init();

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("=== " + dealership.getName() + " ===");
            System.out.println(dealership.getAddress() + " | " + dealership.getPhone());
            System.out.println();
            System.out.println(" 1 - Find vehicles within a price range");
            System.out.println(" 2 - Find vehicles by make / model");
            System.out.println(" 3 - Find vehicles by year range");
            System.out.println(" 4 - Find vehicles by color");
            System.out.println(" 5 - Find vehicles by mileage range");
            System.out.println(" 6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println(" 7 - List ALL vehicles");
            System.out.println(" 8 - Add a vehicle");
            System.out.println(" 9 - Remove a vehicle");
            System.out.println("99 - Quit");
            System.out.println();
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();

            switch (command) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "99":
                    running = false;
                    break;
                default:
                    System.out.println("That is not a command. Enter a number from the menu.");
            }
        }
    }

    private void init() {

    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        System.out.println();
    }

    private void processGetByPriceRequest() {

    }

    private void processGetByMakeModelRequest() {

    }

    private void processGetByYearRequest() {

    }

    private void processGetByColorRequest() {

    }

    private void processGetByMileageRequest() {

    }

    private void processGetByVehicleTypeRequest() {

    }

    private void processGetAllVehiclesRequest() {

    }

    private void processAddVehicleRequest() {

    }

    private void processRemoveVehicleRequest() {

    }

}
