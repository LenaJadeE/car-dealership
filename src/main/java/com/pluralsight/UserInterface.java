package com.pluralsight;

import java.math.BigDecimal;
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
        fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        System.out.println();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles matched.");
            return;
        }

        System.out.printf("%-7s %-5s %-12s %-14s %-7s %-8s %9s %11s%n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");

        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-7d %-5d %-12s %-14s %-7s %-8s %9d %11.2f%n",
                    vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                    vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
        }

        System.out.println(vehicles.size() + " found");
    }

    private void processGetByPriceRequest() {
        BigDecimal min = promptForPrice("Minimum price: ");
        BigDecimal max = promptForPrice("Maximum price: ");

        displayVehicles(dealership.getVehiclesByPrice(min, max));
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
        int vin = promptForInt("VIN: ");

        if (findByVin(vin) != null) {
            System.out.println("A vehicle with VIN " + vin + " is already in the inventory.");
            return;
        }

        int year = promptForInt("Year: ");
        String make = promptForText("Make: ");
        String model = promptForText("Model: ");
        String vehicleType = promptForText("Type (car, truck, SUV, van): ");
        String color = promptForText("Color: ");
        int odometer = promptForInt("Odometer: ");
        BigDecimal price = promptForPrice("Price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);

        System.out.println("Added the " + year + " " + make + " " + model + ".");
    }

    private void processRemoveVehicleRequest() {
        int vin = promptForInt("VIN of the vehicle to remove: ");
        Vehicle vehicle = findByVin(vin);

        if (vehicle == null) {
            System.out.println("No vehicle has the VIN " + vin + ".");
            return;
        }

        dealership.removeVehicle(vehicle);
        fileManager.saveDealership(dealership);

        System.out.println("Removed the " + vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel() + ".");
    }

    private Vehicle findByVin(int vin) {
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        return null;
    }

    private String promptForText(String message) {
        String text = "";

        while (text.isEmpty()) {
            System.out.print(message);
            text = scanner.nextLine().trim();

            if (text.isEmpty()) {
                System.out.println("Please type something.");
            }
        }
        return text;
    }

    private int promptForInt(String s) {
        while (true) {
            System.out.print("VIN of the vehicle to remove: ");

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number, like 2015.");
            }
        }
    }

    private BigDecimal promptForPrice(String message) {
        while (true) {
            System.out.print(message);

            try {
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a price, like 4500 or 4499.99.");
            }
        }
    }
}
