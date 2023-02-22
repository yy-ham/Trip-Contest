package com.example.demo.controller.member;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import com.example.demo.vo.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@RestController
public class MemberAjaxController {
	@Autowired
	@Setter
	private MemberService service;
	
	@RequestMapping("/checkMember")
	public int checkMember(String name, String phone, HttpSession session) {
		
		System.out.println("NAME : "+name);
		System.out.println("phone : "+phone);
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("name", name);
	    map.put("phone", phone);
	    MemberVO member = null;
	    int res = 0;
	    if(service.findByNameAndPhone(map) != null) {
	    	member = service.findByNameAndPhone(map);
	    }
	    
	    if(member != null) {
	    	session.setAttribute("id", member.getId());
	    	res = 1;
	    }
	 
	    return res;
	}

	@RequestMapping("/checkMemberPwd")
	public int checkMemberPwd(String id,String name, String phone, HttpSession session) {
		System.out.println("ID : "+id);
		System.out.println("NAME : "+name);
		System.out.println("phone : "+phone);
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("id", id);
	    map.put("name", name);
	    map.put("phone", phone);
	    MemberVO member = null;
	    int res = 0;
	    if(service.findByIdAndNameAndPhone(map) != null) {
	    	member = service.findByIdAndNameAndPhone(map);
	    }
	    
	    if(member != null) {
	    	session.setAttribute("pwd", member.getPwd());
	    	res = 1;
	    }
	 
	    return res;
	}
}
