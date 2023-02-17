package com.example.demo.korea.dao;

import com.example.demo.korea.entity.Korea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoreaDAO extends JpaRepository<Korea, Integer> {

}
