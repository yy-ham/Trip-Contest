package com.example.demo.plan.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.plan.service.PlanService;

import lombok.Setter;

@Controller
@Setter
public class PlanController {
	@Autowired
	private PlanService service;
	
	public int pageSIZE = 8;
	public int totalRecord = 0;
	public int totalPage = 1;
	
	//여행계획 목록 전체 불러오기
	@GetMapping("/plan/list/{pageNUM}")
	public String list(Model model, @PathVariable int pageNUM, String sort_col) {
		totalRecord = service.getTotalRecord();
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
		model.addAttribute("list", service.findAll(map));
		
		return "/plan/list";
		
	}
	
	//여행계획 상세 불러오기
	@GetMapping("/plan/detail/{plan_no}")
	public String detail(Model model, @PathVariable int plan_no) {
		model.addAttribute("detail", service.findByPlanNo(plan_no));
		return "/plan/detail";
		
	}
	
	
//	@GetMapping("/plan/list/{pageNUM}")
//	public String list(Model model, @PathVariable int pageNUM, String sort_col) {
//		totalRecord = service.getTotalRecord();
//		totalPage = (int) Math.ceil(totalRecord / (double)pageSIZE);
//		int start = (pageNUM-1)*pageSIZE + 1;
//		int end = start +  pageSIZE - 1;
//		if(sort_col != null || sort_col != "") {
//			sort_col = "plan_date";
//		}
//		
//		HashMap<String , Object> map = new HashMap<String, Object>();
//		map.put("start", start);
//		map.put("end", end);
//		map.put("sort_col", sort_col);
//		
//		model.addAttribute("totalPage", totalPage);
//		model.addAttribute("list", service.findAll(map));
//		
//		return "/plan/list";
//		
//	}
//	@GetMapping("/plan/list/{pageNUM}")
//	public String list(Model model, @PathVariable int pageNUM, String sort_col) {
//		totalRecord = service.getTotalRecord();
//		totalPage = (int) Math.ceil(totalRecord / (double)pageSIZE);
//		int start = (pageNUM-1)*pageSIZE + 1;
//		int end = start +  pageSIZE - 1;
//		if(sort_col != null || sort_col != "") {
//			sort_col = "plan_date";
//		}
//		
//		HashMap<String , Object> map = new HashMap<String, Object>();
//		map.put("start", start);
//		map.put("end", end);
//		map.put("sort_col", sort_col);
//		
//		model.addAttribute("totalPage", totalPage);
//		model.addAttribute("list", service.findAll(map));
//		
//		return "/plan/list";
//		
//	}
//	

	
	
}
