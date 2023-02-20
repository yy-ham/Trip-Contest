package com.example.demo.service.plan;

import java.util.HashMap;
import java.util.List;

import org.apache.catalina.Manager;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.plan.PlanDAO;
import com.example.demo.db.plan.PlanDBManager;
import com.example.demo.entity.plan.Plan;

import lombok.Setter;

@Service
@Setter
public class PlanService {
	@Autowired
	private PlanDAO dao;
	
	//jpa
	
	//여행계획 등록 및 수정
	public void save(Plan p) {
		dao.save(p);
	}
	
	//여행계획 삭제
	public void deleteByPlanNo(int plan_no) {
		dao.deleteById(plan_no);
	}
	
	//여행계획 번호로 여행계획 찾기
	public Plan findByPlanNo(int plan_no) {
		return dao.findByPlanNo(plan_no);
	}
	
	
	//mybatis

	//여행계획 목록
	public List<Plan> findAll(HashMap<String, Object> map){
		return dao.findAll();
	}
	//여행계획 목록 (지역별)
	public List<Plan> findByRegion(HashMap<String, Object> map){
		return PlanDBManager.findByRegion(map);
	}
	
	//여행계획 목록 전체 레코드 수 가져오기
	public int getTotalRecord() {
		return PlanDBManager.getTotalRecord();
	}
	
	//여행계획글 다음 번호 가져오기
	public int getNextNo() {
		return dao.getNextNo();
	}
	
	//회원이 찜한 여행계획 목록
	public List<Plan> likedFindByMemberId(String member_id) {
		return PlanDBManager.likedFindByMemberId(member_id);
	}
	
	//여행일수 계산
//	public int countDaysByPlanNo(int plan_no) {
//		return PlanDBManager.countDaysByPlanNo(plan_no);
//	}
	
	public int countDaysByPlanNo(int plan_no) {
		return dao.countDaysByPlanNo(plan_no);
	}
	
	//여행계획 검색
	public List<Plan> searchPlan(HashMap<String, Object> map){
		return PlanDBManager.searchPlan(map);
	}
	
	
}
