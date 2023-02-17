package com.example.demo.member.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.member.entity.Member;

import jakarta.transaction.Transactional;
//memberRepository라고 생
@Repository
public interface MemberDAO extends JpaRepository<Member, Integer> {
//	public Optional<Member> findByIdAndPwd(String id, String pwd);
//	//id찾기
//	public Optional<Member> findByNameAndTel(String name, String tel);
//	//pw 찾기
//	public Optional<Member> findByNameAndIdAndTel(String name, String id,String tel);
}
