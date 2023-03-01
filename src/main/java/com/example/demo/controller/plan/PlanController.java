package com.example.demo.controller.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.liked.Liked;
import com.example.demo.service.korea.KoreaService;
import com.example.demo.service.liked.LikedService;
import com.example.demo.service.plan.PlanService;
import com.example.demo.service.plandetail.PlanDetailService;
import com.example.demo.vo.plan.PlanVO;
import com.example.demo.vo.trip.TripVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class PlanController {
	@Autowired
	private PlanService planService;
	@Autowired
	private PlanDetailService planDetailService;
	@Autowired
	private KoreaService koreaService;
	@Autowired
	private LikedService likedService;
	
	public int pageSIZE = 8;
	public int totalRecord = 0;
	public int totalPage = 1;
	public int pageGROUP  = 5;
	
	//여행계획 목록 전체 불러오기
	@GetMapping("/plan/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int pageNUM, 
			@RequestParam(defaultValue = "plan_date") String orderColumn, String keyword, 
			@RequestParam(defaultValue = "0") int region) {
		
		System.out.println("pageNUM:" + pageNUM);
		System.out.println("keyword:" + keyword);
		System.out.println("region:" + region);
		
		HashMap<String , Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("region", region);
		System.out.println("controller map:" + map);
		
		totalRecord = planService.getTotalRecord(map);
		totalPage = (int) Math.ceil(totalRecord / (double)pageSIZE);

		System.out.println("totalRecord:" + totalRecord);
		System.out.println("totalPage:" + totalPage);
		
		int start = (pageNUM-1)*pageSIZE + 1;
		int end = start +  pageSIZE - 1;
		
		map.put("start", start);
		map.put("end", end);
		map.put("orderColumn", orderColumn);
//		map.put("totalPage", totalPage);
//		map.put("totalRecord", totalRecord);
//		map.put("pageNum", pageNUM);
		
		
		int startPage = (pageNUM-1)/pageGROUP*pageGROUP+1;
		int endPage = startPage+pageGROUP-1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}else if(endPage == 0) {
			endPage = 1;
		}
		
		model.addAttribute("list", planService.findAll(map));
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		System.out.println("start:" + startPage);
		System.out.println("end:" + endPage);
		
		model.addAttribute("regionlist", koreaService.findAll());
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("orderColumn", orderColumn);
		model.addAttribute("region", region); //페이징 처리할 때 필요
		model.addAttribute("pageNUM", pageNUM);
		model.addAttribute("totalPage", totalPage);

//		session.setAttribute("keyword", keyword);
		//model.addAttribute("plan_region", planService.getRegion(plan_no));
		
		return "/plan/list";
	}
	
	
	//여행계획 상세 불러오기
	@GetMapping("/plan/detail/{plan_no}")
	public String detail(Model model, @PathVariable int plan_no) {
		planService.updateHit(plan_no);
		model.addAttribute("plan", planService.findByPlanNo(plan_no));
		model.addAttribute("region", planService.getRegion(plan_no));
		int cnt = planService.countDaysByPlanNo(plan_no);
		model.addAttribute("cnt", cnt);
	
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("plan_no", plan_no);
		for(int i = 1; i <= cnt; i++) {
			map.put("day", i);
			model.addAttribute("detail"+i, planDetailService.getPlanDetail(map));
		}

		return "/plan/detail";
	}
	
	
	//여행계획 작성
	@GetMapping("/plan/insert")
	public void insertForm(Model model, @RequestParam(defaultValue = "1") int pageNUM,
			@RequestParam(defaultValue = "0") int region) {
		
		System.out.println("pageNUM:" + pageNUM);
		System.out.println("region:" + region);
		
		int nextNo = planService.getNextNo();
		System.out.println("nextNo:" + nextNo);
		
		model.addAttribute("nextNo", nextNo);
		model.addAttribute("regionlist", koreaService.findAll());
	}
	
	//여행계획 작성 페이지 - 여행지 목록
	@ResponseBody
	@GetMapping("/plan/insert/list")
	public List<TripVO> getTripList(@RequestParam(defaultValue = "1") int pageNUM, 
			@RequestParam(defaultValue = "0") int region){
		System.out.println("pageNUM:" + pageNUM);
		
		List<TripVO> list = null;
		
		totalRecord = planService.getTotalRecordInInsert(region);
		totalPage = (int) Math.ceil(totalRecord / (double)pageSIZE);
		
		System.out.println("totalRecord:" + totalRecord);
		System.out.println("totalPage:" + totalPage);
		
		int start = (pageNUM-1)*pageSIZE + 1;
		int end = start +  pageSIZE - 1;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("region", region);
		map.put("start", start);
		map.put("end", end);
		
		list = planService.findAllInInsert(map);
		
		return list;
	}
	
	//여행계획 작성 페이지 - 전체 레코드 수
	@ResponseBody
	@GetMapping("/plan/insert/list/record")
	public int getTotalTripList(@RequestParam(defaultValue = "0") int region){
		int re = -1;
		System.out.println("region:" + region);
		re = planService.getTotalRecordInInsert(region);
		totalPage = (int) Math.ceil(totalRecord / (double)pageSIZE);
	

		return re;
	}
	
	//여행계획 작성
	@PostMapping("/plan/insert")
	public ModelAndView insertSubmmit(PlanVO p, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/plan/list");	
		
		//insert plan
		int re = -1;
		re = planService.insert(p);
		System.out.println("plan insert re:" + re);
		
		//insert plandetail
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 5; j++) {
				String name = "day" + i + "_" + j;
				if(request.getParameter(name) != null && !request.getParameter(name).equals("")) {
					System.out.println("name: " + name);
					int trip_no = Integer.parseInt(request.getParameter(name));
					System.out.println("trip_no:" + trip_no);
					planDetailService.insertPlanDetail(p.getPlan_no(), i, trip_no);
				}else {
					break;
				}
			}
		}
		return mav;
	}
	
	//수정
	@GetMapping("/plan/update/{plan_no}")
	public ModelAndView updateForm(Model model, @PathVariable int plan_no) {
		ModelAndView mav = new ModelAndView("/plan/update");	
		System.out.println("planno:" + plan_no);
		mav.addObject("p", planService.findByPlanNo(plan_no));
		mav.addObject("region", planService.getRegion(plan_no));
		mav.addObject("regionlist", koreaService.findAll());
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("plan_no", plan_no);
		int cnt = planService.countDaysByPlanNo(plan_no);
		
		//불러오기
		for(int i = 1; i <= cnt; i++) {
			map.put("day", i);
			mav.addObject("day" + i, planDetailService.getPlanDetail(map));

			List<TripVO> list = planDetailService.getPlanDetail(map);
			for(int j = 0; j < list.size(); j++) {
				int trip_no = list.get(j).getTrip_no() ;
				System.out.println("day" + i + "_" + (j+1));
				System.out.println("update list:" + trip_no);
				mav.addObject("day" + i + "_" + (j+1), trip_no);
				System.out.println("map:" + map);
			}
		}
		
		
		return mav;
	}
	
	@PostMapping("/plan/update")
	public ModelAndView updateSubmmit(PlanVO p, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/plan/detail/" + p.getPlan_no());	
		int re = -1;
		System.out.println("plan:" + p);
		re = planService.updatePlan(p);
		System.out.println("plan update re:" + re);
		System.out.println("planno:" + p.getPlan_no());
		
		planDetailService.deleteByPlanNo(p.getPlan_no());
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 5; j++) {
				String name = "day" + i + "_" + j;
				
				if(request.getParameter(name) != null && !request.getParameter(name).equals("")) {
					int trip_no = Integer.parseInt(request.getParameter(name));
					System.out.println("trip_no:" + trip_no);
					planDetailService.insertPlanDetail(p.getPlan_no(), i, trip_no);
				}else {
					break;
				}
			}
		}
		return mav;
	}
	
	//여행계획 삭제
	@GetMapping("/plan/delete/{plan_no}/{member_id}")
	public ModelAndView delete(@PathVariable int plan_no, @PathVariable String member_id) {
		ModelAndView mav = new ModelAndView("redirect:/plan/list");
		int re = planDetailService.deleteByPlanNo(plan_no);
		System.out.println("re:" + re);
		likedService.deleteLiked(plan_no, member_id);
		planService.deleteByPlanNo(plan_no);
		return mav;
	}
	

	//여행계획 수정 - 이미 선택된 목록 불러오기
	@ResponseBody
	@GetMapping("/plan/update/list")
	public List<TripVO> findByTripNoInUpdate(int plan_no, int day) {
		List<TripVO> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("plan_no", plan_no);
		map.put("day", day);
		list = planDetailService.getPlanDetail(map);
		return list;
		//return trip;
	}

	//여행계획 목록 - 로그인한 유저가 찜한 목록
	@ResponseBody
    @GetMapping("/plan/list/liked/{memberId}")
    public List<Integer> findAllPlanLiked(@PathVariable String memberId) {
    	List<Liked> planLikedList = likedService.findByMemberIdAndType(memberId, "plan");
    	List<Integer> planLikedNoList = new ArrayList<>();
    	for(int i = 0; i < planLikedList.size(); i++) {
    		int no = planLikedList.get(i).getNo();
    		planLikedNoList.add(no);
    	}
    	System.out.println("planLikedNoList" + planLikedNoList);
    	return planLikedNoList;
    }
	
	@ResponseBody
	@GetMapping("/plan/list/region/{code}")
	public String findRegionByCode(@PathVariable int code) {
		return koreaService.findRegionByCode(code);
	}
}
