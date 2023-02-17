package com.example.demo.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.member.dao.MemberDAO;
import com.example.demo.member.entity.Member;
import com.example.demo.member.service.MemberService;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.Table;
import lombok.Setter;

@Controller
@Setter
public class MemberController {
	@Autowired
	private MemberService service;
	//뷰를 보여주기 위한 mapping
		@GetMapping("/join")
		public void insertForm() {
		}
	//등록
		@PostMapping("/join")
		public ModelAndView insertSubmit(Member m) {
			ModelAndView mav = new ModelAndView("redirect:/login");
		    service.insertMember(m);
		    return mav;
		}
	//수정
//	@GetMapping("/member/update/{id}")
//	public ModelAndView update(@PathVariable int no) {
//		ModelAndView mav = new ModelAndView("/member/update");
//		mav.addObject("m",gs.getOne(no));
//		return mav;
//		}
//	//탈퇴
//	@GetMapping("/member/delete/{no}")
//	public ModelAndView delete(@PathVariable int no) {
//		ModelAndView mav = new ModelAndView("redirect:/member/list");
//		gs.deleteById(no);
//		return mav;
//	}
	
	//로그인
	
	//id찾기
	//pw찾기
	
}
