package com.example.demo.service.recoment;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.recoment.RecomentDAO;
import com.example.demo.db.recoment.RecomentDBManager;
import com.example.demo.entity.recoment.Recoment;
import com.example.demo.vo.recoment.RecomentVO;

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
	
	public int insertRecoment(RecomentVO r) {
		return RecomentDBManager.insertRecoment(r);
	}
	
	public int deleteRecoment(int rec_no) {
		return dao.deleteRecoment(rec_no);
	}
	
	public Recoment findByRecNo(int rec_no) {
		return dao.findByRecNo(rec_no);
	}
	
	public int updateRecoment(HashMap<String , Object> map) {
		return RecomentDBManager.updateRecoment(map);
	}
	
}

