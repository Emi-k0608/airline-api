
package com.emikaido.airlineapi.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.emikaido.airlineapi.exception.FlightNotFoundException;
import com.emikaido.airlineapi.model.Flight;
import com.emikaido.airlineapi.repository.FlightRepository;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {
    @Mock
    private FlightRepository flightRepository;
    @InjectMocks
    private FlightService flightService;

    @Test
    void findFlightByNumber_success() {
        // 1. Arrange
        Flight flight = new Flight();
        when(flightRepository.findByFlightNum("AC123"))
            .thenReturn(Optional.of(flight));
        // 2. Act
        Flight result = flightService.findFlightByNumber("AC123");
        // 3. Assert
        assertSame(flight, result);
        // 4. Verify
        verify(flightRepository).findByFlightNum("AC123");
    }
    
	@Test
	void findFlightByNumber_notFound() {
	    // 1. Arrange：該当するFlightが存在しない
	    when(flightRepository.findByFlightNum("AC999"))
	        .thenReturn(Optional.empty());
	    // 2. Act & Assert：例外が発生することを確認
	    assertThrows(
	        FlightNotFoundException.class,
	        () -> flightService.findFlightByNumber("AC999")
	    );
	    // 3. Verify：Repositoryが呼ばれたことを確認
	    verify(flightRepository).findByFlightNum("AC999");
	}

	@Test
	void addFlight_success() {
	    // 1. Arrange
	    Flight flight = new Flight();
	    when(flightRepository.save(flight))
	        .thenReturn(flight);
	    // 2. Act
	    Flight result = flightService.addFlight(flight);
	    // 3. Assert
	    assertSame(flight, result);
	    // 4. Verify
	    verify(flightRepository).save(flight);
	}

}
