package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.PlanVo;
import com.example.demo.vo.TripVo;

@Repository
public class TripSearchDao {
	public List<TripVo> TripSearch(int korea_code){
		return DBManager.TripSearch(korea_code);
	}
	public List<PlanVo> PlanSearch(int korea_code){
		return DBManager.PlanSearch(korea_code);
	}
}
