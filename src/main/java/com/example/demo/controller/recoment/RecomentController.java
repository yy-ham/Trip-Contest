package com.example.demo.controller.recoment;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.recoment.Recoment;
import com.example.demo.service.recoment.RecomentService;
import com.example.demo.vo.recoment.RecomentVO;

@RestController
public class RecomentController {
	@Autowired
	private RecomentService recomentService;
	
	@GetMapping("/recoment/tripList")
	public List<Recoment> list(int no) {
		return recomentService.findByNoAndType(no, "trip");
	}
	
	@GetMapping("/recoment/insert")
	public int insertRecoment(RecomentVO r) {
		System.out.println("rec: " + r);
		int re = recomentService.insertRecoment(r);
		System.out.println("re:" + re);
		return re;
	}
	
	@GetMapping("/recoment/delete")
	public int delete(int recNo) {
		int re = recomentService.deleteRecoment(recNo);
		return re;
	}
	
	@GetMapping("/recoment/get")
	public Recoment findByRecNo(int recNo) {
		Recoment r = recomentService.findByRecNo(recNo);
		System.out.println("r:" + r);
		return r;
	}
	
	@GetMapping("/recoment/update")
	public int updateRecoment(String recContent, int recNo) {
//		r.setRec_no(recNo);
		System.out.println("컨 r:" + recContent);
		System.out.println("컨 recNo:" + recNo);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rec_content", recContent);
		map.put("rec_no", recNo);
		
		int re = recomentService.updateRecoment(map);
		return re;
	}
	
}