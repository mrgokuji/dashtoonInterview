package org.example.model.vehicle;

import org.example.model.PassType;

public class Vehicle {
    String regNumber;
    VehicleType vehicleType;

    public Vehicle(String regNumber, VehicleType vehicleType) {
        this.regNumber = regNumber;
        this.vehicleType = vehicleType;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
