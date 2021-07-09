package com.example.cabBookingApplication.strategy;

import com.example.cabBookingApplication.model.Vehicle;

import java.util.List;

public interface PricingStrategy {
    public Vehicle allocatePrice(List<Vehicle> vehicles);
}