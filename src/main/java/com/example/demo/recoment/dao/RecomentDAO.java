package com.example.demo.recoment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.recoment.entity.Recoment;

public interface RecomentDAO extends JpaRepository<Recoment, Integer> {
	
	public List<Recoment> findByNoAndType(int no, String type);
}
