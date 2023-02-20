package com.example.demo.controller.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.plan.Plan;
import com.example.demo.entity.plandetail.PlanDetail;
import com.example.demo.service.plan.PlanService;
import com.example.demo.service.plandetail.PlanDetailService;
import com.example.demo.service.recoment.RecomentService;

import lombok.Setter;

@Controller
@Setter
public class PlanController {
	@Autowired
	private PlanService planService;
	
	@Autowired
	private PlanDetailService planDetailService;
	
	@Autowired
	private RecomentService recomentService;
	
	public int pageSIZE = 8;
	public int totalRecord = 0;
	public int totalPage = 1;
	
	//여행계획 목록 전체 불러오기
	@GetMapping("/plan/list/{pageNUM}")
	public String list(Model model, @PathVariable int pageNUM, String sort_col) {
		totalRecord = planService.getTotalRecord();
		totalPage = (int) Math.ceil(totalRecord / (double)pageSIZE);
		int start = (pageNUM-1)*pageSIZE + 1;
		int end = start +  pageSIZE - 1;
		if(sort_col != null || sort_col != "") {
			sort_col = "plan_date";
		}
		
		HashMap<String , Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("sort_col", sort_col);
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", planService.findAll(map));

		return "/plan/list";
	}
	
	@GetMapping("/plan/insert")
	public void insertForm() {
		int nextNo = planService.getNextNo();
		System.out.println("nextNo:" + nextNo);
	}
	
	@PostMapping("/plan/insert")
	public int insertSubmmit(Plan p, PlanDetail pd) {
		int re = -1;
		
		return re;
	}
	
	
	//여행계획 상세 불러오기
	@GetMapping("/plan/detail/{plan_no}")
	public String detail(Model model, @PathVariable int plan_no) {
		model.addAttribute("plan", planService.findByPlanNo(plan_no));
		model.addAttribute("cnt", planService.countDaysByPlanNo(plan_no));
		int cnt = planService.countDaysByPlanNo(plan_no);
		ArrayList<Integer> dayList = new ArrayList<Integer>();
		for(int i = 1; i <= cnt; i++) {
			dayList.add(i);
		}
		model.addAttribute("dayList", dayList);
		
		//날짜별 일정도 불러와야함
		return "/plan/detail";
		
	}
	

	
	
}
