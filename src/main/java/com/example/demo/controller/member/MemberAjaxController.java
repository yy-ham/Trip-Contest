package com.example.demo.controller.member;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.member.Member;
import com.example.demo.service.member.MemberService;
import com.example.demo.vo.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@RestController
public class MemberAjaxController {
	@Autowired
	@Setter
	private MemberService memberService;
	
	@RequestMapping("/checkMember")
	public int checkMember(String name, String phone, HttpSession session) {
		
//		System.out.println("NAME : "+name);
//		System.out.println("phone : "+phone);
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("name", name);
	    map.put("phone", phone);
	    MemberVO member = null;
	    int res = 0;
	    if(memberService.findByNameAndPhone(map) != null) {
	    	member = memberService.findByNameAndPhone(map);
	    }
	    
	    if(member != null) {
	    	session.setAttribute("id", member.getId());
	    	res = 1;
	    }
	 
	    return res;
	}

	@RequestMapping("/checkMemberPwd")
	public int checkMemberPwd(String id,String name, String phone, HttpSession session) {
//		System.out.println("ID : "+id);
//		System.out.println("NAME : "+name);
//		System.out.println("phone : "+phone);
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("id", id);
	    map.put("name", name);
	    map.put("phone", phone);
	    MemberVO member = null;
	    int res = 0;
	    if(memberService.findByIdAndNameAndPhone(map) != null) {
	    	member = memberService.findByIdAndNameAndPhone(map);
	    }
	    
	    if(member != null) {
	    	session.setAttribute("pwd", member.getPwd());
	    	res = 1;
	    }
	 
	    return res;
	}
	@RequestMapping("/checkLogin")
	public String checkLogin(String id,String pwd) {
		String result = null;
		Optional<Member> member = memberService.findById(id);
		//일치한다면
		if (member.isPresent() && member.get().getPwd().equals(pwd)) {
			result = "yes";
		}else {
			//로그인 실패 문구
			result = "no";
		}
		return result;
	}

}
