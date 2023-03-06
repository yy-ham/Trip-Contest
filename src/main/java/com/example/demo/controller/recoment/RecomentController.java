package com.example.demo.controller.recoment;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.recoment.Recoment;
import com.example.demo.service.recoment.RecomentService;
import com.example.demo.vo.recoment.RecomentVO;


@Controller
public class RecomentController {
	@Autowired
	private RecomentService recomentService;
	
	//여행계획글 번호로 댓글 목록 불러오기
	@ResponseBody
	@GetMapping("/recoment/list")
	public List<Recoment> list(int no) {
		return recomentService.findByNoAndType(no, "plan");
	}
	
	//댓글 등록하기
	@ResponseBody
	@GetMapping("/recoment/insert")
	public int insertRecoment(RecomentVO r) {
		return recomentService.insertRecoment(r);
	}
	
	//수정 버튼 눌렀을 때 수정하려는 댓글 내용 가져오기
	@ResponseBody
	@GetMapping("/recoment/get")
	public Recoment findByRecNo(int recNo) {
		return recomentService.findByRecNo(recNo);
	}
	
	//댓글 수정하기
	@ResponseBody
	@GetMapping("/recoment/update")
	public int updateRecoment(String rec_content, int rec_no) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rec_content", rec_content);
		map.put("rec_no", rec_no);
		int re = recomentService.updateRecoment(map);
		return re;
	}

	//댓글 삭제하기
	@ResponseBody
	@GetMapping("/recoment/delete")
	public int delete(Model model, int recNo) {
		return recomentService.deleteRecoment(recNo);
	}
}
