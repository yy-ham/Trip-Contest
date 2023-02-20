package com.example.demo.plan.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.plan.entity.Plan;

import jakarta.transaction.Transactional;

@Repository
public interface PlanDAO extends JpaRepository<Plan, Integer> {
	public List<Plan> findAll();
	public Plan findByPlanNo(int plan_no); //여행계획 상세정보 불러오기
	
//	@Modifying
//	@Transactional
//	@Query(value = "insert into plan(plan_no, id, plan_title, plan_date, plan_start, plan_end) "
//			+ "values(seq_plan.nextval, :#{#id}, :#{#plan_title}, sysdate, :#{#plan_start},"
//			+ ":#{#plan_end});", nativeQuery = true)
//	public int insertPlan(Plan plan); //여행계획 등록
//	
//	
//	@Modifying
//	@Transactional
//	@Query(value = "")
//	public int updatePlan(Plan plan);
	
}
