package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.PlanVo;
import com.example.demo.vo.TripVo;

@Repository
public class MainPageDao{
	
	public List<PlanVo> Planerbest(){
		return DBManager.Planerbest();
	}
	
	public List<TripVo> Tripbest(){
		return DBManager.Tripbest();
	}
	
	
}