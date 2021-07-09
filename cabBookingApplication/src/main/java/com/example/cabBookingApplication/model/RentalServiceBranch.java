package com.example.cabBookingApplication.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalServiceBranch {
    int id;
    String name;
    Address address;
    Map<VehicleType, List<Vehicle>> vehicles;
    Map<VehicleType, Integer> priceMap;



    public RentalServiceBranch(String branchName) {
        this.name = branchName;
        this.priceMap = new HashMap<>();
        this.vehicles = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<VehicleType, List<Vehicle>> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Map<VehicleType, List<Vehicle>> vehicles) {
        this.vehicles = vehicles;
    }

    public Map<VehicleType, Integer> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<VehicleType, Integer> priceMap) {
        this.priceMap = priceMap;
    }
}
