package com.example.demo.service.korea;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.korea.KoreaDAO;
import com.example.demo.entity.korea.Korea;

@Service
public class KoreaService {
	@Autowired
	private KoreaDAO dao;
	
	public List<Korea> findAll(){
		return dao.findAll();
	}
	
	public String findRegionByCode(int code) {
		return dao.findRegionByCode(code);
	}
}
