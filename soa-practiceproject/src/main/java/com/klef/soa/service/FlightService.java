package com.klef.soa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klef.soa.entity.Flight;

@Service
public interface FlightService {
	Flight addFlight(Flight fid);
	Flight updateFlight(Flight fid);
	String deleteFlight(long fid);
	List<Flight> displayAll();
	Flight displayById(long fid);
	List<Flight> findByAirline(String airline);
	List<Flight> findByDestination(String destination);

}
