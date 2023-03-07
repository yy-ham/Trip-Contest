package com.example.demo.controller.recoment;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.recoment.Recoment;
import com.example.demo.service.plan.PlanService;
import com.example.demo.service.recoment.RecomentService;
import com.example.demo.vo.recoment.RecomentVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RecomentController {
	@Autowired
	private RecomentService recomentService;
	
	@GetMapping("/recoment/list")
	public List<Recoment> planList(int no) {
//		model.addAttribute("list", recomentService.findByNoAndType(no, "plan"));
		return recomentService.findByNoAndType(no, "plan");
	}
	
	@GetMapping("/recoment/tripList")
	public List<Recoment> tripList(int no) {
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
	public int delete(Model model, int recNo) {
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
	public int updateRecoment(String rec_content, int rec_no) {
//		r.setRec_no(recNo);
		System.out.println("컨 r:" + rec_content);
		System.out.println("컨 recNo:" + rec_no);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rec_content", rec_content);
		map.put("rec_no", rec_no);
		
		int re = recomentService.updateRecoment(map);
		return re;
	}
	
}

