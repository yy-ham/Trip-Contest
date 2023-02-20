package com.example.demo.service.days;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.days.DaysDAO;
import com.example.demo.db.days.DaysDBManager;
import com.example.demo.entity.days.Days;
import com.example.demo.entity.plan.Plan;

import lombok.Setter;

@Service
@Setter
public class DaysService {
	@Autowired
	private DaysDAO dao;
	
	//날짜별 일정 전체 조회
	public List<Days> findAll() {
		return dao.findAll();
	}
	
	//여행계획글 번호로 날짜별 일정 조회
	public List<Days> findByPlanNo(int plan_no){
		return dao.findByPlanNo(plan_no);
	}
	
	//여행계획글 삭제시 해당 일정 전체 삭제
	public void deleteByPlanNo(int plan_no) {
		dao.deleteById(plan_no);
	}
	
	//날짜별 일정 등록
	public int insertDays(Days d) {
		return DaysDBManager.insertDays(d);
	}
	
	//날짜별 일정 수정
	public int updateDays(Days d) {
		return DaysDBManager.updateDays(d);
	}
	
	
	

	
}
