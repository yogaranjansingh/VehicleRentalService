package com.example.cabBookingApplication.service;

import com.example.cabBookingApplication.model.RentalServiceBranch;
import com.example.cabBookingApplication.model.TimeWindow;
import com.example.cabBookingApplication.model.Vehicle;
import com.example.cabBookingApplication.model.VehicleType;
import com.example.cabBookingApplication.repository.RentalServiceRepository;
import com.example.cabBookingApplication.strategy.PricingStrategy;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VehicleService {

    RentalServiceRepository rentalServiceRepository;
    PricingStrategy pricingStrategy;

    public VehicleService(PricingStrategy pricingStrategy)
    {
        this.pricingStrategy = pricingStrategy;
    }

    public void addVehicle(String branchName, String id, VehicleType vehicleType){
        Vehicle vehicle = new Vehicle(id, vehicleType);
        RentalServiceBranch branch = rentalServiceRepository.rentalServiceBranches.get(branchName);
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        if(branch.getVehicles()==null || branch.getVehicles().size()==0)
        {
            vehicleList.add(vehicle);
            branch.getVehicles().put(vehicleType, vehicleList);
        }
        else
        {
            if(branch != null)
            {
                if(branch.getVehicles().get(vehicleType)!=null)
                {
                    branch.getVehicles().get(vehicleType).add(vehicle);
                }
            }

        }
    }

    public Map<String, List<Vehicle>> getVehicleInventory(Date startTime, Date endTime) {
        return null;
    }

    public Vehicle bookVehicle(VehicleType vehicleType, Date startTime, Date endTime) {
        List<Vehicle> availableVehicles = getAvailableVehicles(vehicleType, startTime, endTime);
        Vehicle assignedVehicle = pricingStrategy.allocatePrice(availableVehicles);
        TimeWindow timeWindow = new TimeWindow(startTime, endTime);
        assignedVehicle.getBookedDurations().add(timeWindow);
        return assignedVehicle;
    }

    private List<Vehicle> getAvailableVehicles(VehicleType vehicleType, Date startTime, Date endTime) {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        for(Map.Entry<String, RentalServiceBranch> branch : rentalServiceRepository.rentalServiceBranches.entrySet()) {
             RentalServiceBranch rentalServiceBranch = branch.getValue();

             for(Map.Entry<VehicleType, List<Vehicle>> e : rentalServiceBranch.getVehicles().entrySet())
             {
                 for(Vehicle vehicle : e.getValue())
                 {
                     if(vehicle.getVehicleType() == vehicleType) {
                         List<TimeWindow> durations = vehicle.getBookedDurations();
                         for (TimeWindow duration : durations) {
                             if (endTime.before(duration.getStartTime()) || startTime.after(duration.getEndTime()))
                                 vehicles.add(vehicle);
                         }
                     }
                 }
             }
        }
        return vehicles;
    }
}
