package com.example.demo.dao.liked;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.liked.Liked;

import jakarta.transaction.Transactional;

@Repository
public interface LikedDAO extends JpaRepository<Liked, Integer>{

	// 해당 아이디의 찜목록
	List<Liked> findByMemberIdAndType(String memberId, String type);

	Optional<Liked> findByNoAndTypeAndMemberId(int no, String type, String memberId);
	
	@Modifying
	@Transactional
	@Query(value = "delete liked WHERE NO = ?1 AND TYPE = 'plan' AND MEMBER_ID = ?2", nativeQuery = true)
	public int deleteLiked(int no, String member_id);
}