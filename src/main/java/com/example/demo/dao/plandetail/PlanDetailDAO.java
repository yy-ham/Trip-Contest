package com.example.demo.dao.plandetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.plandetail.PlanDetail;

@Repository
public interface PlanDetailDAO extends JpaRepository<PlanDetail, Integer> {

	//여행계획 상세 - 날짜별 일정 조회
	public List<PlanDetail> findByPlanNo(int plan_no);

	
}