package com.example.cabBookingApplication.controller;

import com.example.cabBookingApplication.model.Vehicle;
import com.example.cabBookingApplication.model.VehicleType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public interface RentalServiceController {

    void addBranch(String branchName);
    void addVehicle(String vehicleId, VehicleType vehicleType, String branchName);
    Map<String, Map<VehicleType, List<Vehicle>>> viewVehicleInventory(Date startTime, Date endTime);

}
