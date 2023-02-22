package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MainPageDao;
import com.example.demo.dao.TripSearchDao;

public class TripSearchPageController {

	@Autowired
	private TripSearchDao tripsearchdao;
	

	
//	@RequestMapping("/mainpage3")
//	public ModelAndView tripsearch() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("tripsearch", tripsearchdao.TripSearch());
//		return mav;
//	}
}
