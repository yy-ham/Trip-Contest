package com.example.demo.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.member.dao.MemberDAO;
import com.example.demo.member.entity.Member;

import lombok.Setter;


@Service
public class MemberService{
	
	@Autowired
	@Setter
	private MemberDAO memberDAO;
	//등록
    public Member insertMember(Member m) {
    	return memberDAO.save(m);
    }
    //탈퇴
    public void deleteMember(Member m) {
        memberDAO.delete(m);
    }
}