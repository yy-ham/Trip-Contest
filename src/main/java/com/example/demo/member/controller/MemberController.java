package com.example.demo.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.member.dao.MemberDAO;
import com.example.demo.member.entity.Member;
import com.example.demo.member.service.MemberService;

import jakarta.persistence.Table;
import lombok.Setter;

@Controller
@Setter
@Table(name = "member")
public class MemberController {
	@Autowired
	private MemberService service;
	//등록
	@PostMapping("/member/join")
	public ModelAndView save(Member m) {
		ModelAndView mav = new ModelAndView("redirect:/member/login");
		service.save(m);
		return mav;
	}
	//로그인
	//id찾기
	//pw찾기
	//수정
	//탈퇴
	
}
