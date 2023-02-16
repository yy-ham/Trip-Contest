package com.example.demo.trip.service;

import com.example.demo.trip.dao.TripDAO;
import com.example.demo.trip.entity.Trip;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter
public class TripService {

    @Autowired
    private TripDAO tripDAO;

    public int getNextTripNo(){
        return tripDAO.getNextTripNo();
    }

    public Trip findByTripNo(int tripNo){
        return tripDAO.findByTripNo(tripNo);
    }

}
