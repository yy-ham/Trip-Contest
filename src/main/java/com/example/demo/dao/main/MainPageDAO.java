package com.example.demo.dao.main;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.main.MainDBManager;
import com.example.demo.vo.plan.PlanVO;
import com.example.demo.vo.trip.TripVO;



@Repository
public class MainPageDAO{
	
	public List<PlanVO> Planerbest(){
		return MainDBManager.Planerbest();
	}
	
	public List<TripVO> Tripbest(){
		return MainDBManager.Tripbest();
	}
	
	
}