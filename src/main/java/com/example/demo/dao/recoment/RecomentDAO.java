package com.example.demo.dao.recoment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.recoment.Recoment;

import jakarta.transaction.Transactional;

public interface RecomentDAO extends JpaRepository<Recoment, Integer> {
	
	@Query(value = "select * from recoment where no = ?1 and type = ?2 "
			+ "order by rec_date desc", nativeQuery = true)
	public List<Recoment> findByNoAndType(int no, String type);
	
	@Query(value = "select * from recoment where rec_no = ?", nativeQuery = true)
	public Recoment findByRecNo(int recNo);
	
	@Modifying
	@Transactional
	@Query(value = "delete recoment where rec_no = ?", nativeQuery = true)
	public int deleteRecoment(int recNo);
	
}