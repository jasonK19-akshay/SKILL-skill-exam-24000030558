
package com.klef.soa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

import com.klef.soa.entity.Flight;
import com.klef.soa.repository.FlightRepository;

@DataJpaTest
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:postgresql://localhost:5432/Skill-exam-2400030558",
    "spring.datasource.username=postgres",
    "spring.datasource.password=admin123",
    "spring.datasource.driver-class-name=org.postgresql.Driver",
    "spring.jpa.hibernate.ddl-auto=update"
})
public class FlightRepositoryTest {

    @Autowired
    private FlightRepository repository;

    private Flight flight1;
    private Flight flight2;
    private long initialCount;

    @BeforeEach
    void setup() {

        initialCount = repository.count();

        flight1 = new Flight();
        flight1.setAirline("Air India");
        flight1.setSource("Hyderabad");
        flight1.setDestination("Delhi");
        flight1.setFare(5000.0);
        flight1.setDepartureTime("10:00 AM");

        flight2 = new Flight();
        flight2.setAirline("Indigo");
        flight2.setSource("Chennai");
        flight2.setDestination("Mumbai");
        flight2.setFare(4500.0);
        flight2.setDepartureTime("02:00 PM");

        flight1 = repository.saveAndFlush(flight1);
        flight2 = repository.saveAndFlush(flight2);
    }

    // Test Case 1 - Find All
    @Test
    void testFindAllFlights() {

        List<Flight> flights = repository.findAll();

        assertNotNull(flights);
        assertFalse(flights.isEmpty());

        assertTrue(flights.stream()
                .anyMatch(f -> f.getId() == flight1.getId()));

        assertTrue(flights.stream()
                .anyMatch(f -> f.getId() == flight2.getId()));

        System.out.println("Total Flights: " + flights.size());
    }

    // Test Case 2 - Find By ID
    @Test
    void testFindFlightById() {

        Optional<Flight> flight =
                repository.findById(flight1.getId());

        assertTrue(flight.isPresent());

        assertEquals(flight1.getId(), flight.get().getId());

        Optional<Flight> invalidFlight =
                repository.findById(-1L);

        assertFalse(invalidFlight.isPresent());

        System.out.println("Flight ID: " + flight.get().getId());
        System.out.println("Airline: " + flight.get().getAirline());
    }

    // Test Case 3 - Derived Query
    @Test
    void testFindFlightByAirlineAndDestination() {

        List<Flight> byAirline =
                repository.findByAirline("Air India");

        assertNotNull(byAirline);
        assertFalse(byAirline.isEmpty());

        assertTrue(byAirline.stream()
                .anyMatch(f -> f.getId() == flight1.getId()));

        assertEquals("Air India", flight1.getAirline());

        List<Flight> byDestination =
                repository.findByDestination("Mumbai");

        assertNotNull(byDestination);
        assertFalse(byDestination.isEmpty());

        assertTrue(byDestination.stream()
                .anyMatch(f -> f.getId() == flight2.getId()));

        assertEquals("Mumbai", flight2.getDestination());

        System.out.println("Airline: " + flight1.getAirline());
        System.out.println("Destination: " + flight2.getDestination());
    }

    // Test Case 4 - Count
    @Test
    void testTotalFlightCount() {

        long count = repository.count();

        assertEquals(initialCount + 2, count);

        System.out.println("Total Flights: " + count);
    }

    // Test Case 5 - Entity Details
    @Test
    void testFlightDetails() {

        Optional<Flight> flight =
                repository.findById(flight1.getId());

        assertTrue(flight.isPresent());

        Flight foundFlight = flight.get();

        assertNotNull(foundFlight.getAirline());
        assertNotNull(foundFlight.getSource());
        assertNotNull(foundFlight.getDestination());
        assertNotNull(foundFlight.getDepartureTime());
        assertNotNull(foundFlight.getCreatedAt());
        assertNotNull(foundFlight.getUpdatedAt());

        assertTrue(foundFlight.getId() > 0);

        assertEquals("Air India", foundFlight.getAirline());
        assertEquals("Hyderabad", foundFlight.getSource());
        assertEquals("Delhi", foundFlight.getDestination());
        assertEquals(5000.0, foundFlight.getFare());
        assertEquals("10:00 AM", foundFlight.getDepartureTime());

        System.out.println("ID: " + foundFlight.getId());
        System.out.println("Airline: " + foundFlight.getAirline());
        System.out.println("Source: " + foundFlight.getSource());
        System.out.println("Destination: " + foundFlight.getDestination());
        System.out.println("Fare: " + foundFlight.getFare());
        System.out.println("Departure: " + foundFlight.getDepartureTime());
    }
}
