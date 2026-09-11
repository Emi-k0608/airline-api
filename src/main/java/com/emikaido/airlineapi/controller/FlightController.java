package com.emikaido.airlineapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

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
	
	@PostMapping("/flights")
	@ResponseStatus(HttpStatus.CREATED) // 201 Created を返す
	public Flight addFlight(@RequestBody Flight flight) {
		return flightService.addFlight(flight);
	}

}
