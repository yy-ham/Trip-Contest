package com.example.demo.controller.plandetail;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.plandetail.PlanDetail;
import com.example.demo.service.plandetail.PlanDetailService;

@Controller
public class PlanDetailController {
	@Autowired
	private PlanDetailService service;
	

	
	
}
