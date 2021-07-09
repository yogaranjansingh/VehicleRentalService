package com.example.cabBookingApplication.strategy;

import com.example.cabBookingApplication.model.Vehicle;
import com.example.cabBookingApplication.model.VehicleType;

import java.util.List;

public class LowestPricingStrategy implements PricingStrategy{

    @Override
    public Vehicle allocatePrice(List<Vehicle> vehiclesAvailable) {
        int minPrice = Integer.MAX_VALUE;
        Vehicle lowestPriceVehicle = null;
        for(Vehicle vehicle : vehiclesAvailable)
        {
            if(vehicle.getPrice()<minPrice)
            {
                minPrice = vehicle.getPrice();
                lowestPriceVehicle = vehicle;
            }
        }
        return lowestPriceVehicle;
    }

}
