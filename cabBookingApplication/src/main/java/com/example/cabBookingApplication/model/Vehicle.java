package com.example.cabBookingApplication.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vehicle {
    String id;
    VehicleType vehicleType;
    boolean isAvailable;
    int price;
    List<TimeWindow> bookedDurations;

    public List<TimeWindow> getBookedDurations() {
        return bookedDurations;
    }

    public void setBookedDurations(List<TimeWindow> bookedDurations) {
        this.bookedDurations = bookedDurations;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Vehicle(String id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.bookedDurations = new ArrayList<>();
    }
}
