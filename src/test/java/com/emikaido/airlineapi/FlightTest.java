package com.emikaido.airlineapi;

import org.junit.jupiter.api.Test;

import com.emikaido.airlineapi.model.Flight;
import com.emikaido.airlineapi.model.FlightStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightTest {
	
	@Test
	void getOriginTest(){
		// Arrange
		Flight flight = new Flight();
		flight.setOrigin("YVR");
		
	    // Act
		String origin = flight.getOrigin();
		
	    // Assert
		assertEquals("YVR", origin);
	}
	
	@Test
	void getStatusTest(){
		// Arrange
		Flight flight = new Flight();
		flight.setStatus(FlightStatus.DELAYED);
		
	    // Act
		FlightStatus status = flight.getStatus();
		
	    // Assert
		assertEquals(FlightStatus.DELAYED, status);
	}
}
