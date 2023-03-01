package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.TripSearchDao;
import com.example.demo.db.DBManager;
import com.example.demo.vo.PlanVo;
import com.example.demo.vo.TripVo;

@Controller
public class TripSearchPageController {

	@Autowired
	private TripSearchDao tripsearchdao;

	@RequestMapping("/tripsearchpage")
	public ModelAndView list(@RequestParam(value = "korea_code", defaultValue = "1") int korea_code) {
		ModelAndView mav = new ModelAndView("tripsearchpage");
		mav.addObject("TripSearch", tripsearchdao.TripSearch(korea_code));
		mav.addObject("PlanSearch", tripsearchdao.PlanSearch(korea_code));
		return mav;
	}	
	
	@RequestMapping("/tripajax")
	@ResponseBody
	public List<TripVo>trip(@RequestParam(value = "korea_code", defaultValue = "0") int korea_code){
		return tripsearchdao.TripSearch(korea_code);
		
		
		
				
	}
	@RequestMapping("/planajax")
	@ResponseBody
	public List<PlanVo>plan(@RequestParam(value = "korea_code", defaultValue = "0") int korea_code){
		return tripsearchdao.PlanSearch(korea_code);
		
		
		
	}
	
	@RequestMapping("/regionajax")
	@ResponseBody
	public String getResion(int korea_code){
		return DBManager.getRion(korea_code);
	} 
		
		


}
