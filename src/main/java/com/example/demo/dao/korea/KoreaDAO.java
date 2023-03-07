package com.example.demo.dao.korea;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.korea.Korea;

@Repository
public interface KoreaDAO extends JpaRepository<Korea, Integer> {
	
	@Query(value = "select region from korea where code = ?", nativeQuery = true)
	public String findRegionByCode(int code);

}
