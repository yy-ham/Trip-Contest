package com.example.demo.dao.main;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.example.demo.db.main.MainDBManager;
import com.example.demo.vo.trip.TripVO;

@Repository
public class TripSearchDAO {
	public List<TripVO> TripSearch(int korea_code){
		return MainDBManager.TripSearch(korea_code);
	}
	public List<TripVO> PlanSearch(int korea_code){
		return MainDBManager.PlanSearch(korea_code);
	}
	
}
