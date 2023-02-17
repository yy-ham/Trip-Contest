package com.example.demo.recoment.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.plan.service.PlanService;
import com.example.demo.recoment.entity.Recoment;
import com.example.demo.recoment.service.RecomentService;

@Controller
public class RecomentController {
	@Autowired
	private RecomentService service;
	
	@GetMapping("/recoment/list")
	public void list(Model model, int no) {
		model.addAttribute("list", service.findByNoAndType(no, "plan"));
	}
	
	@GetMapping("/recoment/save")
	public void save(Model model, Recoment re) {
		service.saveRecoment(re);
	}
	
	@GetMapping("/recoment/delete")
	public void delete(Model model, int rec_no) {
		service.deleteByRecNo(rec_no);
	}
	
	
}
