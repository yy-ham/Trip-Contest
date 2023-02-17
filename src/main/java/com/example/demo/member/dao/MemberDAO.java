package com.example.demo.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.member.entity.Member;

import jakarta.transaction.Transactional;

@Repository
public interface MemberDAO extends JpaRepository<Member, Integer> {
//	@Modifying
//	@Query(value = "insert into Member m(m.id,m.pwd,m.name,m.jumin,m.addr,m.phone,m.mail,m.gender,m.grade,m.member_img) values(:#{#m.id},:#{#m.pwd},:#{#m.name},:#{m.jumin},:#{#m.addr},:#{#m.phone},:#{#m.mail},:#{#m.gender},:#{#m.grade})", nativeQuery = true)
//	@Transactional
//	public void insert(Member m);
	//회원가입,수정
	public Member save(Member member);
	//로그인
	public Member findByIdAndPwd(String id, String pwd);
	//id찾기
	public Member findByNameAndTel(String name, String tel);
	//pw 찾기
	public Member findByNameAndIdAndTel(String name, String id,String tel);
	//회원탈퇴
	void delete(Member member);
	
	
}
