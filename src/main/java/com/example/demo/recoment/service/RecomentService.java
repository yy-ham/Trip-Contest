package com.example.demo.recoment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.recoment.dao.RecomentDAO;
import com.example.demo.recoment.entity.Recoment;

@Service
public class RecomentService {
	@Autowired
	private RecomentDAO dao;
	
	public List<Recoment> findAll(){
		return dao.findAll();
	}
	
	public List<Recoment> findByNoAndType(int no, String type){
		return dao.findByNoAndType(no, type);
	}
	
	public void saveRecoment(Recoment re) {
		dao.save(re);
	}
	
	public void deleteByRecNo(int rec_no) {
		dao.deleteById(rec_no);
	}
	
	
}
