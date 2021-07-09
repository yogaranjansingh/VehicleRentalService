package com.example.cabBookingApplication.controller;

import com.example.cabBookingApplication.model.Vehicle;
import com.example.cabBookingApplication.model.VehicleType;
import com.example.cabBookingApplication.service.RentalBranchService;
import com.example.cabBookingApplication.service.VehicleService;

import java.util.Date;

public class VehicleControllerImpl implements  VehicleController{

    VehicleService vehicleService;
    RentalBranchService rentalBranchService;

    public VehicleControllerImpl(VehicleService vehicleService, RentalBranchService rentalBranchService)
    {
        this.vehicleService = vehicleService;
        this.rentalBranchService = rentalBranchService;
    }

    @Override
    public void allocatePrice(String branchName, VehicleType vehicleType, int price) {
        rentalBranchService.allocatePrice(branchName, vehicleType, price);
    }

    @Override
    public Vehicle bookVehicle(VehicleType vehicleType, Date startTime, Date endTime) {
        System.out.println("Booked Vehicle : ");
       return vehicleService.bookVehicle(vehicleType, startTime, endTime);
    }
}
