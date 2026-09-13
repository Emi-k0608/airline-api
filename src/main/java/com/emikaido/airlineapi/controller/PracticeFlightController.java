package com.emikaido.airlineapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emikaido.airlineapi.model.Flight;
import com.emikaido.airlineapi.service.PracticeFlightService;

@RestController
public class PracticeFlightController {

	private PracticeFlightService practiceFlightService;
	
	public PracticeFlightController(PracticeFlightService practiceFlightService) {
	    this.practiceFlightService = practiceFlightService;
	}
	
	@GetMapping("/practice/flights")
    public List<Flight> flights() {
		return practiceFlightService.getAllFlights();
	}
	
	@PostMapping("/practice/flights")
	public Flight addFlight(@RequestBody Flight flight) {
		return practiceFlightService.addFlight(flight);
	}

}
