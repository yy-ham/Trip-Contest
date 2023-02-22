package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MainPageDao;
import com.example.demo.vo.PlanVo;

import lombok.Setter;

@Controller
public class MainPageController {
	
	
	@Autowired
	private MainPageDao mainpagedao;
	

	
	@RequestMapping("/mainpage")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("mainpage");
		mav.addObject("planerbest", mainpagedao.Planerbest());
		mav.addObject("tripbest", mainpagedao.Tripbest());
		return mav;
	}
	
	
}	



