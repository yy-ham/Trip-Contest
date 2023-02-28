package com.example.demo.controller.liked;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.example.demo.db.liked.DBManager;
import com.example.demo.service.liked.LikedService;


import lombok.Setter;


@Controller
@Setter
public class LikedController {

	@Autowired
	private LikedService likedService;
	
	
	
	/*
	 * @GetMapping("/Liked/likeList/{member_id}") public String
	 * findByIdandPlan(Model model, @PathVariable String member_id) {
	 * model.addAttribute("likedListPlan", likedService.findByIdandPlan(member_id));
	 * model.addAttribute("likedListTrip", likedService.findByIdandTrip(member_id));
	 * System.out.println(likedService.findByIdandTrip(member_id));
	 * 
	 * return "/Liked/likeList"; }
	 */
	
	@GetMapping("/Liked/likeList/{member_id}")
	public ModelAndView list(@PathVariable String member_id) {
		ModelAndView mav = new ModelAndView("/Liked/likeList");
		mav.addObject("likedListPlan", likedService.findByIdandPlan(member_id));
		mav.addObject("likedListTrip", likedService.findByIdandTrip(member_id));
		return mav;
	}
	
	
	
	
	
	@GetMapping("/Liked/deleteLiked/{like_no}")
	public ModelAndView deleteLiked(@PathVariable int like_no) {
		ModelAndView mav = new ModelAndView("redirect:/Liked/likeList");
		likedService.deleteLiked(like_no);
		mav.addObject("member_id", "hong");
		mav.addObject("type", "plan");
		mav.addObject("no", 1);
		
		return mav;
	}
	
}
