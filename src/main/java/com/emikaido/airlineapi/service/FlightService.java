package com.emikaido.airlineapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.emikaido.airlineapi.model.Flight;
import com.emikaido.airlineapi.repository.FlightRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FlightService {

	// FlightRepository
	private final FlightRepository flightRepository;
	
	// constructorを作る
	public FlightService (FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}
	
	// 全便を返すmethod
	public List<Flight> getAllFlights(){
		return flightRepository.findAll();
	}
	
	// 受け取った Flight をListに追加する
	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	// 1便検索を行う
	public Flight findFlightByNumber(String flightNum) {
		Flight foundFlight
			= flightRepository.findByFlightNum(flightNum)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found: " + flightNum));
		return foundFlight;
	}

}
