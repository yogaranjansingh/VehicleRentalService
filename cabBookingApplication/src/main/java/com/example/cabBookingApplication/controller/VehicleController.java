package com.example.cabBookingApplication.controller;

import com.example.cabBookingApplication.model.Vehicle;
import com.example.cabBookingApplication.model.VehicleType;

import java.util.Date;

public interface VehicleController {
    void allocatePrice(String branchName, VehicleType vehicleType, int price);
    Vehicle bookVehicle(VehicleType vehicleType, Date startTime, Date endTime);
}
