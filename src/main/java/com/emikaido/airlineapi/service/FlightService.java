package com.emikaido.airlineapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.emikaido.airlineapi.model.Flight;
import com.emikaido.airlineapi.model.FlightStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FlightService {
	
	// Flightのリストを保持する
	private List<Flight> flights;
	
	// constructorを作る
	public FlightService () {
		this.flights = new ArrayList<>();
		flights.add(new Flight("AC101", "Vancouver", "Toronto", FlightStatus.ON_TIME));
		flights.add(new Flight("AC102", "Vancouver", "HND", FlightStatus.DELAYED));
		flights.add(new Flight("AC103", "LAX", "Toronto", FlightStatus.CANCELLED));
	}
	
	// 全便を返すmethod
	public List<Flight> getAllFlights(){
		return flights;
	}
	
	// 受け取った Flight をListに追加する
	public Flight addFlight(Flight flight) {
		flights.add(flight);
		return flight;
	}
	
	// 1便検索を行う
	public Flight findFlightByNumber(String flightNum) {
		Flight foundFlight
			= flights.stream().filter(flight -> flight.getFlightNum().equals(flightNum)).findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found: " + flightNum));
		return foundFlight;
	}

}
