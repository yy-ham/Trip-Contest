package com.example.demo.controller.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.main.MainPageDAO;

import lombok.Setter;

@Controller
public class MainPageController {
	
	
	@Autowired
	private MainPageDAO mainpagedao;
	

	
	@RequestMapping("/mainpage")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("/main/mainpage");
		mav.addObject("planerbest", mainpagedao.Planerbest());
		mav.addObject("tripbest", mainpagedao.Tripbest());
		return mav;
	}
	
	
}	



