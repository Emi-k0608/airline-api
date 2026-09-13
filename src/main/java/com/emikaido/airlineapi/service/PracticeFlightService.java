package com.emikaido.airlineapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emikaido.airlineapi.model.Flight;
import com.emikaido.airlineapi.model.FlightStatus;

@Service
public class PracticeFlightService {
	
	// Flight情報をリストに保管
	private List<Flight> flights;
	
	// constructor3便追加
	public PracticeFlightService() {
		this.flights = new ArrayList<>();
		flights.add(new Flight("AC101", "LAX", "TYO", FlightStatus.CANCELLED));
		flights.add(new Flight("AC102", "TYO", "HIJ", FlightStatus.DELAYED));
		flights.add(new Flight("AC103", "YVR", "HND", FlightStatus.ON_TIME));
	}
	
	// Flight情報取得
	public List<Flight> getAllFlights() {
		return this.flights;
	}
	
	// 引数で受け取ったflightを追加する
	public Flight addFlight(Flight flight) {
		this.flights.add(flight);
		return flight;
	}
}
