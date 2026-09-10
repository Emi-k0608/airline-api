package com.emikaido.airlineapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emikaido.airlineapi.model.Flight;
import com.emikaido.airlineapi.service.FlightService;


@RestController
public class FlightController {
	private FlightService flightService;
	
	public FlightController(FlightService flightService) {
	    this.flightService = flightService;
	}
	
	@GetMapping("/flights")
    public  List<Flight> flights() {
		return flightService.getAllFlights();
	
	}

}
