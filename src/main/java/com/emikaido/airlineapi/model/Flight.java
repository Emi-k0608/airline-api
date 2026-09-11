package com.emikaido.airlineapi.model;

public class Flight {
	private String flightNum;
	private String origin;
	private String destination;
	private FlightStatus status;

	// 引数なしconstructor
	public Flight() {
		
	}
	
	// constructor
	public Flight(String flightNum, String origin, String destination, FlightStatus status) {
			this.flightNum = flightNum;
			this.origin = origin;
			this.destination = destination;
			this.status = status;
	}
	
	// getter
	public String getFlightNum() {
		return this.flightNum;
	}

	public String getOrigin() {
		return this.origin;
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	public FlightStatus getStatus() {
		return this.status;
	}
	
	// setter
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public void setStatus(FlightStatus status) {
		this.status = status;
	}
}
