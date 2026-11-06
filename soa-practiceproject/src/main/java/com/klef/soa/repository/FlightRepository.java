package com.klef.soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.soa.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{
	List<Flight> findByAirline(String airline);
	List<Flight> findByDestination(String destination);

}
