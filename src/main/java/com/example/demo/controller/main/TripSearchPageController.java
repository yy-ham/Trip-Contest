package com.example.demo.controller.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.example.demo.dao.main.TripSearchDAO;
import com.example.demo.vo.trip.TripVO;

@Controller
public class TripSearchPageController {

	@Autowired
	private TripSearchDAO tripsearchdao;

	@RequestMapping("/tripsearchpage")
	public ModelAndView list(@RequestParam(value = "korea_code", defaultValue = "0") int korea_code) {
		ModelAndView mav = new ModelAndView("/main/tripsearchpage");
		mav.addObject("TripSearch", tripsearchdao.TripSearch(korea_code));
//		mav.addObject("planer", tripsearchdao.Planer());
		return mav;
	}	
	
	@RequestMapping("/tripajax")
	@ResponseBody
	public List<TripVO>trip(@RequestParam(value = "korea_code", defaultValue = "0") int korea_code){
		return tripsearchdao.TripSearch(korea_code);
		
		
		
				
	}
	@RequestMapping("/planajax")
	@ResponseBody
	public List<TripVO>plan(@RequestParam(value = "korea_code", defaultValue = "0") int korea_code){
		return tripsearchdao.PlanSearch(korea_code);
		
		
		
				
	}
	


}
