package com.example.demo.dao.plan;

import java.util.HashMap;
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
	
	//여행계획 상세정보 불러오기
	public Plan findByPlanNo(int plan_no); 
	
	//여행일수 계산
	@Query(value = "select plan_end - plan_start + 1 from plan where plan_no = ?", 
			nativeQuery = true)
	public int countDaysByPlanNo(int plan_no);
  
	
	//여행계획 목록
	@Query(value = "select plan_no, plan_title, plan_img, member_id"
			+ "	from (select rownum n, a.*"
			+ "	from (select * from plan order by :#{#map.sort_col} desc) a)"
			+ "	where n between :#{#map.start} and :#{#map.end}",
			nativeQuery = true)
	public Plan findAll(HashMap<String, Object> map);
	
	
	@Query(value = "select nvl(max(plan_no), 0) + 1 from plan", nativeQuery = true)
	public int getNextNo();
	
}