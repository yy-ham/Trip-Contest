package com.example.demo.service.plandetail;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.plandetail.PlanDetailDAO;
import com.example.demo.db.plandetail.PlanDetailDBManager;
import com.example.demo.entity.plan.Plan;
import com.example.demo.entity.plandetail.PlanDetail;
import com.example.demo.entity.trip.Trip;
import com.example.demo.vo.trip.TripVO;

import lombok.Setter;

@Service
@Setter
public class PlanDetailService {
	@Autowired
	private PlanDetailDAO dao;
	
	public void save(PlanDetail pd) {
		dao.save(pd);
	}
	
	//여행계획글 번호로 날짜별 일정 조회
	public List<PlanDetail> findByPlanNo(int plan_no){
		return dao.findByPlanNo(plan_no);
	}
	
	//여행계획글 삭제시 해당 일정 전체 삭제
	public int deleteByPlanNo(int plan_no) {
		return dao.deletePlanDetail(plan_no);
	}
	
	public int insertPlanDetail(int plan_no, int day, int trip_no) {
		return dao.insertPlanDetail(plan_no, day, trip_no);
	}
	
	public List<TripVO> getPlanDetail(HashMap<String, Object> map){
		return PlanDetailDBManager.getPlanDetail(map);
	}
	
	//날짜별 일정 등록
	public int insertPlanDetail(PlanDetail d) {
		return PlanDetailDBManager.insertDays(d);
	}
	
	//날짜별 일정 수정
	public int updatePlanDetail(PlanDetail d) {
		return PlanDetailDBManager.updatePlanDetail(d);
	}
	
	public String findTripImg(int trip_no) {
		return PlanDetailDBManager.findTripImg(trip_no);
	}

	
	
	
	public int getLiked(int plan_no) {
		return dao.getLiked(plan_no);
	}
	
	public int getUserLiked(int no, String member_id) {
		return dao.getUserLiked(no, member_id);
	}
	
	public int insertLiked(String member_id, int no, int trip_no, String liked_title) {
		return dao.insertLiked(member_id, no, trip_no, liked_title);
	}
	
	public int deleteLiked(int no) {
		return dao.deleteLiked(no);
	}
	
	public int addLikee(int plan_no) {
		return dao.addLiked(plan_no);
	}
	public int cancelLiked(int plan_no) {
		return dao.cancelLiked(plan_no);
	}
	
	public String getImg(int trip_no) {
		return dao.getImg(trip_no);
	}
}
