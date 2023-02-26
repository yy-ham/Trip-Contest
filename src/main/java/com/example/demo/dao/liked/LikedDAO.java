package com.example.demo.dao.liked;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.liked.Liked;

@Repository
public interface LikedDAO extends JpaRepository<Liked, Integer>{

	// 해당 아이디의 찜목록
	List<Liked> findByMemberId(String memberId);
}
