package com.example.demo.dao.days;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.days.Days;

@Repository
public interface DaysDAO extends JpaRepository<Days, Integer> {

	//여행계획 상세 - 날짜별 일정 조회
	public List<Days> findByPlanNo(int plan_no);

	
}