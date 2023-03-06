package com.example.demo.service.plan;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.plan.PlanDAO;
import com.example.demo.db.plan.PlanDBManager;
import com.example.demo.entity.plan.Plan;
import com.example.demo.vo.plan.PlanVO;
import com.example.demo.vo.trip.TripVO;

import lombok.Setter;

@Service
@Setter
public class PlanService {
	@Autowired
	private PlanDAO planDAO;
	
	//여행계획 목록 페이지
	//여행계획 목록
	public List<Plan> findAll(HashMap<String, Object> map){
		return PlanDBManager.findAll(map);
	}
	
	//여행계획 목록 전체 레코드 수 가져오기
	public int getTotalRecord(HashMap<String, Object> map) {
		return PlanDBManager.getTotalRecord(map);
	}
	
	
	//여행계획 상세 페이지
	//여행일수 계산
	public int countDaysByPlanNo(int plan_no) {
		return planDAO.countDaysByPlanNo(plan_no);
	}
	
	//조회수 증가
	public int updateHit(int plan_no) {
		return planDAO.updateHit(plan_no);
	}
	
	//여행계획 번호로 여행계획 기본정보 찾기
	public Plan findByPlanNo(int plan_no) {
		return planDAO.findByPlanNo(plan_no);
	}
	
	//지역 코드 -> 지역명으로 바꾸기
	public String getRegion(int plan_no) {
		return planDAO.getRegion(plan_no);
	}
	
	
	//여행계획 작성 페이지 form
	//여행계획글 다음 번호 가져오기
	public int getNextNo() {
		return planDAO.getNextNo();
	}
	
	//여행계획 작성 페이지 - 전체 레코드 수 가져오기 (ajax)
	public int getTotalRecordInInsert(int region) {
		return PlanDBManager.getTotalRecordInInsert(region);
	}
	
	//여행계획 작성 페이지 - 여행지 목록 불러오기 (ajax)
	public List<TripVO> findAllInInsert(HashMap<String, Object> map){
		return PlanDBManager.findAllInInsert(map);
	}
	
	
	//여행계획 작성 페이지 submit
	//여행계획 등록
	public int insertPlan(PlanVO p) {
		return PlanDBManager.insertPlan(p);
	}
	
	
	//여행계획 수정 페이지 submit
	//여행계획 수정
	public int updatePlan(PlanVO p) {
		return PlanDBManager.updatePlan(p);
	}
	
	
	//여행계획 삭제
	public void deleteByPlanNo(int plan_no) {
		planDAO.deleteById(plan_no);
	}

	
	//여행계획 작성 - 여행일수 가져오기 (ajax)
	public int countDaysInInsert(HashMap<String, Object> map) {
		return PlanDBManager.countDaysInInsert(map);
	}
}
