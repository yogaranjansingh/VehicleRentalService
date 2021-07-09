package com.example.cabBookingApplication.repository;

import com.example.cabBookingApplication.model.RentalServiceBranch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RentalServiceRepository {

    public static Map<String, RentalServiceBranch> rentalServiceBranches = new ConcurrentHashMap<>();

}
