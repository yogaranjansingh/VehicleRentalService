package com.example.cabBookingApplication.controller;

import com.example.cabBookingApplication.model.RentalServiceBranch;
import com.example.cabBookingApplication.model.Vehicle;
import com.example.cabBookingApplication.model.VehicleType;
import com.example.cabBookingApplication.repository.RentalServiceRepository;
import com.example.cabBookingApplication.service.RentalBranchService;
import com.example.cabBookingApplication.service.VehicleService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class RentalServiceControllerImpl implements  RentalServiceController{


    VehicleService vehicleService;
    RentalBranchService rentalBranchService;

    public RentalServiceControllerImpl(VehicleService vehicleService, RentalBranchService rentalBranchService)
    {
        this.vehicleService = vehicleService;
        this.rentalBranchService = rentalBranchService;
    }

    @Override
    public void addBranch(String branchName) {
        rentalBranchService.addBranch(branchName);
    }

    @Override
    public void addVehicle(String vehicleId, VehicleType vehicleType, String branchName) {
        vehicleService.addVehicle(branchName, vehicleId, vehicleType);
    }

    @Override
    public Map<VehicleType, List<Vehicle>> viewVehicleInventory(Date startTime, Date endTime) {
        //return vehicleService.getVehicleInventory(startTime, endTime);
        return null;
    }
}
