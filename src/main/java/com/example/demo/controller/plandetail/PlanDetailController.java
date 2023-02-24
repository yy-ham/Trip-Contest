package com.example.demo.controller.plandetail;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.plandetail.PlanDetail;
import com.example.demo.service.plan.PlanService;
import com.example.demo.service.plandetail.PlanDetailService;

@RestController
public class PlanDetailController {
	@Autowired
	private PlanDetailService  planDetailService;
	@Autowired
	private PlanService planService;

	@GetMapping("/plan/insert/count")
	public int count(String plan_start, String plan_end) {
		System.out.println(plan_end);
		System.out.println(plan_start);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("plan_start", plan_start);
		map.put("plan_end", plan_end);
		
		System.out.println("map:" + map);
		int re = planService.count(map);
		
		return re;
	}
	
	@GetMapping("/plan/detail/add/liked")
	public int addLiked(int plan_no) {
		System.out.println(plan_no);
		int re = planDetailService.addLikee(plan_no);
		return re;
	}
	@GetMapping("/plan/detail/cancel/liked")
	public int cancelLiked(int plan_no) {
		int re = planDetailService.addLikee(plan_no);
		return re;
	}
	
	@GetMapping("/plan/detail/liked")
	public int getLiked(int plan_no) {
		int re = -1;
		re = planDetailService.getLiked(plan_no);
		
		return re;
	}
	
	//아이디로 찜했는지 확인
	@GetMapping("/plan/detail/liked/{member_id}")
	public int getUserLiked(int no, @PathVariable String member_id) {
		System.out.println(no);
		System.out.println(member_id);
		int re = -1;
		re = planDetailService.getUserLiked(no, member_id);
		
		return re;
	}
	
	//찜 등록
	@GetMapping("/plan/detail/liked/insert")
	public int insertLiked(String member_id, int no, int trip_no, String liked_title) {
		int re = -1;
		int re1 = planDetailService.insertLiked(member_id, no, trip_no, liked_title);
		int re2 = planDetailService.addLikee(no);
		
		if(re1 == 1 && re2 == 1) {
			re = 1;
		}
		return re;
	}
	
	//찜 삭제
	@GetMapping("/plan/detail/liked/delete")
	public int deleteLiked(int no) {
		int re = -1;
		int re1 = planDetailService.deleteLiked(no);
		int re2 = planDetailService.cancelLiked(no);
		
		if(re1 == 1 && re2 == 1) {
			re = 1;
		}
		return re;
	}
	
	@GetMapping("plan/detail/getImg")
	public String getImg(int trip_no) {
		String img = "";
		img = planDetailService.getImg(trip_no);
		return img;
	}
	
}
