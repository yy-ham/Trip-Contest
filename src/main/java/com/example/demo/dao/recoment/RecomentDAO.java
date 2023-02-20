package com.example.demo.dao.recoment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.recoment.Recoment;

public interface RecomentDAO extends JpaRepository<Recoment, Integer> {
	
	public List<Recoment> findByNoAndType(int no, String type);
	
	
}