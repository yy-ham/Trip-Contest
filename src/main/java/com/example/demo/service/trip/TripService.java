package com.example.demo.service.trip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.trip.TripDAO;
import com.example.demo.entity.trip.Trip;

@Service
public class TripService {

	@Autowired
	private TripDAO tripDAO;
	
	public List<Trip> findAll(){
		return tripDAO.findAll();
	}
}
