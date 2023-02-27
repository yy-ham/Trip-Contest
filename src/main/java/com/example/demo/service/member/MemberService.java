package com.example.demo.service.member;

import java.util.HashMap;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.db.member.MemberDBManager;
import com.example.demo.entity.member.*;
import com.example.demo.vo.member.MemberVO;

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
    //수정
    public Member updateMember(Member m) {
    	return memberDAO.save(m);
    }
    //findbyid null이냐 아니냐 (id가)(삭제 수정(조건이 조금더 세부적이어야 함) 모두 마찬가지로)
    //회원가입 시 로그인의 논리들을 적는 메소드를 확인해주어야 함
    public Optional<Member> findById(String id) {
    	return memberDAO.findById(id);
    }
    
    public MemberVO findByNameAndPhone(HashMap<String, Object> map){
    	return MemberDBManager.findByNameAndPhone(map);
    }
    public MemberVO findByIdAndNameAndPhone(HashMap<String, Object> map){
    	return MemberDBManager.findByIdAndNameAndPhone(map);
    }
    
}