package com.example.demo.service.plandetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.plandetail.PlanDetailDAO;
import com.example.demo.db.plandetail.PlanDetailDBManager;
import com.example.demo.entity.plan.Plan;
import com.example.demo.entity.plandetail.PlanDetail;

import lombok.Setter;

@Service
@Setter
public class PlanDetailService {
	@Autowired
	private PlanDetailDAO dao;
	
	//여행계획글 번호로 날짜별 일정 조회
	public List<PlanDetail> findByPlanNo(int plan_no){
		return dao.findByPlanNo(plan_no);
	}
	
	//여행계획글 삭제시 해당 일정 전체 삭제
	public void deleteByPlanNo(int plan_no) {
		dao.deleteById(plan_no);
	}
	
	//날짜별 일정 등록
	public int insertPlanDetail(PlanDetail d) {
		return PlanDetailDBManager.insertDays(d);
	}
	
	//날짜별 일정 수정
	public int updatePlanDetail(PlanDetail d) {
		return PlanDetailDBManager.updateDays(d);
	}
	

	

	
}
