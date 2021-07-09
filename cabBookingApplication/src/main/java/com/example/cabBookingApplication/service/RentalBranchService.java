package com.example.cabBookingApplication.service;

import com.example.cabBookingApplication.model.RentalServiceBranch;
import com.example.cabBookingApplication.model.VehicleType;
import com.example.cabBookingApplication.repository.RentalServiceRepository;

public class RentalBranchService {

    RentalServiceRepository rentalServiceRepository;

    public void addBranch(String branchName)
    {
        RentalServiceBranch rentalServiceBranch = new RentalServiceBranch(branchName);
        rentalServiceRepository.rentalServiceBranches.put(branchName, rentalServiceBranch);
        System.out.println("Rental branch added : "+rentalServiceBranch.toString());
    }

    public void allocatePrice(String branchName, VehicleType vehicleType, int price) {
        RentalServiceBranch rentalServiceBranch = rentalServiceRepository.rentalServiceBranches.get(branchName);
        rentalServiceBranch.getPriceMap().put(vehicleType, price);
    }
}
