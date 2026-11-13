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
}