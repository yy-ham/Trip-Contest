package com.example.demo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.plan.Plan;
import com.example.demo.vo.plan.PlanVO;

import jakarta.transaction.Transactional;

@Repository
public interface PlanDAO extends JpaRepository<Plan, Integer> {
	
	//여행계획 상세 페이지
	
	//여행계획 상세정보 불러오기
	@Query(value = "select plan_no, member_id, plan_title, to_char(plan_date, 'yyyy-mm-dd') plan_date,"
			+ "to_char(plan_start, 'yyyy-mm-dd') plan_start, to_char(plan_end, 'yyyy-mm-dd') plan_end, plan_hit,"
			+ "plan_liked, plan_img, korea_code from plan where plan_no = ?",
			nativeQuery = true)
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
	
	@Query(value = "select region from korea k, plan p "
			+ "where k.code = p.korea_code and p.plan_no = ?", nativeQuery = true)
	public String getRegion(int plan_no);
	
	
	@Modifying
	@Transactional
	@Query(value = "update plan set plan_hit = plan_hit + 1 where plan_no = ?",
	nativeQuery = true)
	public int updateHit(int plan_no);
	
	//여행계획 작성 시 여행일수 계산
	@Query(value = "SELECT TO_DATE(?1, 'YYYY-MM-DD') - "
			+ "TO_DATE(?2, 'YYYY-MM-DD') + 1 FROM dual", nativeQuery = true)
	public int count(String plan_end, String plan_start);

}