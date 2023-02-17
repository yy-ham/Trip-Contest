package com.example.demo.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.member.dao.MemberDAO;

import lombok.Setter;


@Service
public interface MemberService extends MemberDAO {
	
}
