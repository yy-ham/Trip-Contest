package com.example.demo.dao.plan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.plan.Plan;

import jakarta.transaction.Transactional;

@Repository
public interface PlanDAO extends JpaRepository<Plan, Integer> {
	
	//여행계획 상세 페이지
	public Plan findByPlanNo(int plan_no); //여행계획 상세정보 불러오기
	

}