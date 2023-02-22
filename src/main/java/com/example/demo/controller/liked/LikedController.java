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
	
	
	
	@GetMapping("/Liked/likeList")
	public void findByIdandtype(Model model, @RequestParam(value="member_id") String member_id,@RequestParam(value="type") String type) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("member_id", "hong");
		map.put("type", "plan");
		map.put("no", 1); 
 		model.addAttribute("likedListPlan", likedService.findByIdandType(map));
//		model.addAttribute("likedListTrip", likedService.findByIdandType(map));
 		
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
