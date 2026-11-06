package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Flight;
import com.klef.soa.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightRepository repo;

	@Override
	public Flight addFlight(Flight fid) {
		return repo.save(fid);
	}

	@Override
	public Flight updateFlight(Flight fid) {
		// TODO Auto-generated method stub
		Optional<Flight> optionalflight = repo.findById(fid.getId());
		if(optionalflight.isPresent()) {
			Flight f = optionalflight.get();
			f.setFare(fid.getFare());
			f.setDepartureTime(fid.getDepartureTime());
			return repo.save(f);
		}else
		return null;
	}

	@Override
	public String deleteFlight(long fid) {
		boolean flag = repo.existsById(fid);
		if(flag) {
			repo.deleteById(fid);
			return "Flight deleted successfully";
		}else {
			return "Flight is not available ";
		}
		
	}

	@Override
	public List<Flight> displayAll() {
		return repo.findAll();
	}

	@Override
	public Flight displayById(long fid) {
		return repo.findById(fid).orElse(null);
	}

	@Override
	public List<Flight> findByAirline(String airline) {
		return repo.findByAirline(airline);
	}

	@Override
	public List<Flight> findByDestination(String destination) {
		return repo.findByDestination(destination);
	}
	


	
	

	

}
