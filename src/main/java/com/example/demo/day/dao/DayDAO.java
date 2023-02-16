package com.example.demo.day.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.day.entity.Day;

@Repository
public interface DayDAO extends JpaRepository<Day, Integer> {

	
}
