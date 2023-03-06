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
	private RecomentDAO recomentDAO;
	
	
	//여행계획글 번호로 댓글 목록 불러오기
	public List<Recoment> findByNoAndType(int no, String type){
		return recomentDAO.findByNoAndType(no, type);
	}
	
	//댓글 등록하기
	public int insertRecoment(RecomentVO r) {
		return RecomentDBManager.insertRecoment(r);
	}
	
	//수정 버튼 눌렀을 때 수정하려는 댓글 내용 가져오기
	public Recoment findByRecNo(int rec_no) {
		return recomentDAO.findByRecNo(rec_no);
	}
	
	//댓글 수정하기
	public int updateRecoment(HashMap<String , Object> map) {
		return RecomentDBManager.updateRecoment(map);
	}

	//댓글 삭제하기
	public int deleteRecoment(int rec_no) {
		return recomentDAO.deleteRecoment(rec_no);
	}
	
	
}
