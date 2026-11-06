package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.entity.Flight;
import com.klef.soa.service.FlightService;

@RestController
@RequestMapping("flight")
public class FlightController {
    
	@Autowired
	private FlightService service;

    @GetMapping("/")
    public String home() {
    	return "Spring Boot Project";
    }
	
	@PostMapping("add")
	public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
		return ResponseEntity.status(201).body(service.addFlight(flight));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateFlight(@RequestBody Flight flight) {
		Flight f = service.updateFlight(flight);
		if(f!=null) {
			return ResponseEntity.status(200).body(f);
		}else {
			return ResponseEntity.status(404).body("Hostel Not Found");
		}
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteFlight(@RequestBody long id) {
		return ResponseEntity.ok(service.deleteFlight(id));
	}
	
	@GetMapping("displayall")
	public ResponseEntity<List<Flight>> displayAllFlights() {
		return ResponseEntity.ok(service.displayAll());
	}
	
	@GetMapping("display/{id}")
	public ResponseEntity<?> displayFlightById(@PathVariable long id) {
		Flight f = service.displayById(id);
		if(f!=null) {
			return ResponseEntity.ok(f);
		}else {
			return ResponseEntity.status(404).body("ID not found");
		}
	}
	
	@GetMapping("airline/{airline}")
	public ResponseEntity<List<Flight>> displayByAirline(@PathVariable String airline) {
		return ResponseEntity.ok(service.findByAirline(airline));
	}
	
	@GetMapping("destination/{destination}")
	public ResponseEntity<List<Flight>> displayFlightByDestination(@PathVariable String destination) {
		return ResponseEntity.ok(service.findByDestination(destination));
	}

}
