package com.example.demo.service.trip;

import com.example.demo.dao.trip.TripDAO;
import com.example.demo.db.trip.TripDBManager;
import com.example.demo.entity.trip.Trip;
import com.example.demo.vo.trip.TripVO;

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
    
    // 여행지의 전체 레코드를 가져온다.
    public List<TripVO> findAll(Map<String, Object> map){
        return TripDBManager.findAll((HashMap<String, Object>) map);
    }
    
    // 여행지의 전체 레코드 갯수를 가져온다.
    public int getTotal(HashMap<String, Object> map){
        return TripDBManager.getTotalRecord(map);
    }
}
