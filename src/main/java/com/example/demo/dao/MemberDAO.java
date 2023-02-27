package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.member.Member;

//memberRepository라고 생
@Repository
public interface MemberDAO extends JpaRepository<Member, String> {
//	//id찾기
	@Query(value="select * from member where name = ?1 and phone = ?2",nativeQuery = true)
	public Optional<Member> findByNameAndPhone(String name, String phone);
//	//pw 찾기
	@Query(value="select * from member where id = ?1 and name = ?2 and phone = ?3",nativeQuery = true)
	public Optional<Member> findByIdAndNameAndPhone(String id,String name, String phone);
	
}
