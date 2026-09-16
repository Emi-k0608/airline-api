package com.emikaido.airlineapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emikaido.airlineapi.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	// 1便検索を行う
	Optional<Flight> findByFlightNum(String flightNum);
	
}
