package com.example.demo.trip.service;

import com.example.demo.trip.dao.TripDAO;
import com.example.demo.trip.db.TripDBManager;
import com.example.demo.trip.entity.Trip;
import com.example.demo.trip.vo.TripVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TripService {

    @Autowired
    @Setter
    private TripDAO tripDAO;

    public int getNextTripNo(){
        return tripDAO.getNextTripNo();
    }

    public Trip findByTripNo(int tripNo){
        return tripDAO.findByTripNo(tripNo);
    }

    public List<TripVO> findAll(Map<String, Object> map){
        return TripDBManager.findAll((HashMap<String, Object>) map);
    }

    public int getTotal(HashMap<String, Object> map){
        return TripDBManager.getTotalRecord(map);
    }
}
