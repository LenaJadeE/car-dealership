package com.pluralsight;

import java.math.BigDecimal;

public class Vehicle {
    private final int vin;
    private final int year;
    private final String make;
    private final String model;
    private final String vechicleType;
    private final String color;
    private final int odometer;
    private final BigDecimal price;

    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, BigDecimal price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vechicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

}
