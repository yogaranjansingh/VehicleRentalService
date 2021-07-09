package com.example.cabBookingApplication.service;

import com.example.cabBookingApplication.model.RentalServiceBranch;
import com.example.cabBookingApplication.model.TimeWindow;
import com.example.cabBookingApplication.model.Vehicle;
import com.example.cabBookingApplication.model.VehicleType;
import com.example.cabBookingApplication.repository.RentalServiceRepository;
import com.example.cabBookingApplication.strategy.PricingStrategy;

import java.sql.Time;
import java.util.*;

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
        System.out.println("Vehicle Added : " + vehicle.toString());
    }

    public Map<String, Map<VehicleType, List<Vehicle>>> getVehicleInventory(Date startTime, Date endTime) {
        Map<String, Map<VehicleType, List<Vehicle>>> inventory = new HashMap<String, Map<VehicleType, List<Vehicle>>>();
        inventory.put("Available", getAvailableInventory(startTime, endTime));
        inventory.put("UnAvailable", getUnAvailableInventory(startTime, endTime));
        return inventory;
    }

    private Map<VehicleType, List<Vehicle>> getAvailableInventory(Date startTime, Date endTime) {
        HashMap<VehicleType, List<Vehicle>> map = new HashMap<>();
        List<Vehicle> sedanVehicles = new ArrayList<Vehicle>();
        List<Vehicle> hatchBackVehicles = new ArrayList<Vehicle>();
        List<Vehicle> suvVehicles = new ArrayList<Vehicle>();
        for(Map.Entry<String, RentalServiceBranch> branch : rentalServiceRepository.rentalServiceBranches.entrySet()) {
            RentalServiceBranch rentalServiceBranch = branch.getValue();

            for(Map.Entry<VehicleType, List<Vehicle>> e : rentalServiceBranch.getVehicles().entrySet())
            {
                for(Vehicle vehicle : e.getValue())
                {
                        List<TimeWindow> durations = vehicle.getBookedDurations();
                        vehicle.setAvailable(true);
                        for (TimeWindow duration : durations) {
                            if (endTime.after(duration.getStartTime()) || startTime.before(duration.getEndTime())) {
                                vehicle.setAvailable(false);
                                break;
                            }
                        }
                        if(vehicle.isAvailable())
                        {
                            switch (vehicle.getVehicleType())
                            {
                                case Sedan:
                                    sedanVehicles.add(vehicle);
                                    break;

                                case SUV:
                                    suvVehicles.add(vehicle);
                                    break;

                                case Hatchback:
                                    hatchBackVehicles.add(vehicle);
                                    break;

                                default:
                            }
                        }
                }
            }
        }
        map.put(VehicleType.Sedan, sedanVehicles);
        map.put(VehicleType.Hatchback, hatchBackVehicles);
        map.put(VehicleType.SUV, suvVehicles);
        return map;
    }

    private Map<VehicleType, List<Vehicle>> getUnAvailableInventory(Date startTime, Date endTime) {
        HashMap<VehicleType, List<Vehicle>> map = new HashMap<>();
        List<Vehicle> sedanVehicles = new ArrayList<Vehicle>();
        List<Vehicle> hatchBackVehicles = new ArrayList<Vehicle>();
        List<Vehicle> suvVehicles = new ArrayList<Vehicle>();
        for(Map.Entry<String, RentalServiceBranch> branch : rentalServiceRepository.rentalServiceBranches.entrySet()) {
            RentalServiceBranch rentalServiceBranch = branch.getValue();

            for(Map.Entry<VehicleType, List<Vehicle>> e : rentalServiceBranch.getVehicles().entrySet())
            {
                for(Vehicle vehicle : e.getValue())
                {
                    List<TimeWindow> durations = vehicle.getBookedDurations();
                    vehicle.setAvailable(true);
                    for (TimeWindow duration : durations) {
                        if (endTime.after(duration.getStartTime()) || startTime.before(duration.getEndTime())) {
                            vehicle.setAvailable(false);
                            break;
                        }
                    }
                    if(!vehicle.isAvailable())
                    {
                        switch (vehicle.getVehicleType())
                        {
                            case Sedan:
                                sedanVehicles.add(vehicle);
                                break;

                            case SUV:
                                suvVehicles.add(vehicle);
                                break;

                            case Hatchback:
                                hatchBackVehicles.add(vehicle);
                                break;

                            default:
                        }
                    }
                }
            }
        }
        map.put(VehicleType.Sedan, sedanVehicles);
        map.put(VehicleType.Hatchback, hatchBackVehicles);
        map.put(VehicleType.SUV, suvVehicles);
        return map;
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
                         vehicles.add(vehicle);
                         for (TimeWindow duration : durations) {
                             if (endTime.after(duration.getStartTime()) || startTime.before(duration.getEndTime())) {
                                 vehicles.remove(vehicle);
                                 break;
                             }
                         }
                     }
                 }
             }
        }
        return vehicles;
    }
}
