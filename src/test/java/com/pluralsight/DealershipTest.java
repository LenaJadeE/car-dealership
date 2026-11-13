package com.pluralsight;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealershipTest {
    private Dealership createDealership() {
        Dealership dealership = new Dealership("Test Cars", "1 Main St", "555-0100");
        dealership.addVehicle(new Vehicle(1, 1993, "Ford", "Explorer", "SUV", "Red", 525123, new BigDecimal("995.00")));
        dealership.addVehicle(new Vehicle(2, 2001, "Ford", "Ranger", "truck", "Yellow", 172544, new BigDecimal("1995.00")));
        dealership.addVehicle(new Vehicle(3, 2012, "Honda", "Civic", "car", "Gray", 103221, new BigDecimal("6995.00")));
        dealership.addVehicle(new Vehicle(4, 2019, "Toyota", "Camry", "car", "Red", 45210, new BigDecimal("18995.00")));
        return dealership;
    }

    @Test
    void getVehiclesByPrice_should_includeVehiclesAtTheLimits() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByPrice(new BigDecimal("995.00"), new BigDecimal("1995.00"));

        int actualCount = results.size();
        assertEquals(2, actualCount);
    }

    @Test
    void getVehiclesByPrice_should_skipVehiclesOutsideTheRange() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByPrice(new BigDecimal("5000"), new BigDecimal("10000"));

        int actualVin = results.get(0).getVin();
        assertEquals(3, actualVin);
    }

    @Test
    void getVehiclesByPrice_should_returnEmptyListWhenNothingMatches() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByPrice(new BigDecimal("50000"), new BigDecimal("60000"));

        int actualCount = results.size();
        assertEquals(0, actualCount);
    }

    @Test
    void getVehiclesByMakeModel_should_matchBothMakeAndModel() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByMakeModel("Ford", "Ranger");

        int actualVin = results.get(0).getVin();
        assertEquals(2, actualVin);
    }

    @Test
    void getVehiclesByMakeModel_should_ignoreCapitals() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByMakeModel("honda", "CIVIC");

        int actualCount = results.size();
        assertEquals(1, actualCount);
    }

    @Test
    void getVehiclesByYear_should_includeBothEndYears() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByYear(2001, 2012);

        int actualCount = results.size();
        assertEquals(2, actualCount);
    }

    @Test
    void getVehiclesByMakeModel_should_notMatchWhenOnlyTheMakeMatches() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByMakeModel("Ford", "Civic");

        int actualCount = results.size();
        assertEquals(0, actualCount);
    }

    @Test
    void getVehiclesByColor_should_findEveryVehicleOfThatColor() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByColor("Red");

        int actualCount = results.size();
        assertEquals(2, actualCount);
    }

    @Test
    void getVehiclesByColor_should_ignoreCapitals() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByColor("gRaY");

        int actualVin = results.get(0).getVin();
        assertEquals(3, actualVin);
    }

    @Test
    void getVehiclesByMileage_should_returnEmptyListWhenNothingMatches() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByMileage(0, 1000);

        int actualCount = results.size();
        assertEquals(0, actualCount);
    }

    @Test
    void getVehiclesByType_should_findEveryVehicleOfThatType() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.getVehiclesByType("car");

        int actualCount = results.size();
        assertEquals(2, actualCount);
    }

    @Test
    void searchVehicles_should_returnEmptyListWhenFiltersCannotAllMatch() {
        Dealership dealership = createDealership();

        ArrayList<Vehicle> results = dealership.searchVehicles("Honda", new BigDecimal("2000"), "");

        int actualCount = results.size();
        assertEquals(0, actualCount);
    }
}