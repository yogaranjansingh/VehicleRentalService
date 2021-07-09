package com.example.cabBookingApplication;

import com.example.cabBookingApplication.controller.RentalServiceController;
import com.example.cabBookingApplication.controller.RentalServiceControllerImpl;
import com.example.cabBookingApplication.controller.VehicleController;
import com.example.cabBookingApplication.controller.VehicleControllerImpl;
import com.example.cabBookingApplication.model.VehicleType;
import com.example.cabBookingApplication.repository.RentalServiceRepository;
import com.example.cabBookingApplication.service.RentalBranchService;
import com.example.cabBookingApplication.service.VehicleService;
import com.example.cabBookingApplication.strategy.LowestPricingStrategy;
import com.example.cabBookingApplication.strategy.PricingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class CabBookingApplication {

	public static void main(String[] args) {
		PricingStrategy pricingStrategy = new LowestPricingStrategy();
		VehicleService vehicleService = new VehicleService(pricingStrategy);
		RentalBranchService rentalBranchService = new RentalBranchService();
		RentalServiceController rentalServiceController = new RentalServiceControllerImpl(vehicleService, rentalBranchService);
		VehicleController vehicleController = new VehicleControllerImpl(vehicleService, rentalBranchService);
		rentalServiceController.addBranch("Vasant-Vihar");
		rentalServiceController.addBranch("Cyber-City");

		rentalServiceController.addVehicle("DL 01 MR 9310", VehicleType.Sedan, "Vasant-Vihar");
		rentalServiceController.addVehicle("DL 01 MR 9311", VehicleType.Sedan, "Cyber-City");

		vehicleController.allocatePrice("Vasant-Vihar", VehicleType.Sedan, 100);
		vehicleController.allocatePrice("Vasant-Vihar", VehicleType.Hatchback, 80);
		vehicleController.allocatePrice("Cyber-City", VehicleType.Sedan, 200);
		vehicleController.allocatePrice("Cyber-City", VehicleType.Hatchback, 50);

		rentalServiceController.addVehicle("DL 01 MR 9312", VehicleType.Hatchback,"Cyber-City");

		vehicleController.bookVehicle(VehicleType.Sedan,new Date(), new Date());
		SpringApplication.run(CabBookingApplication.class, args);
	}

}
