package com.example.demo.service.search;

import com.example.demo.service.plan.PlanService;
import com.example.demo.service.trip.TripService;
import com.example.demo.vo.plan.PlanVO;
import com.example.demo.vo.trip.TripVO;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Setter
@Getter
public class SearchService {

	// 검색에 사용할 지역변수들
    private final int pageSize = 4;
    private int totalRecord = 0;
    private int totalPage = 1;
    private final int pageGroup = 5;
    private final Map<String,Object> map = new HashMap<>();


    @Autowired
    private TripService tripService;
    
    @Autowired
    private PlanService planService;
    
    public int getStartPlanPage(int pageNum) {
    	int startPage = (pageNum-1)/pageGroup*pageGroup+1;
    	return startPage;

    }
    
    public int getStartTripPage(int pageNum) {
    	int startPage = (pageNum-1)/pageGroup*pageGroup+1;
    	return startPage;

    }
    
    public int getEndPlanPage(int startPage) {
    	int endPage = startPage+pageGroup-1;
    	return endPage;
    }
    
    public int getEndTripPage(int startPage) {
    	int endPage = startPage+pageGroup-1;
    	return endPage;
    }

    
    public int getTotalPlanPage() {
    	 totalRecord = planService.getTotalRecord((HashMap<String, Object>) map);

         if(totalRecord%pageSize==0){
             totalPage = totalRecord / pageSize;
         }else {
             totalPage = (totalRecord / pageSize) + 1;
         }
         
         return totalPage;
    }
    
    public int getTotalTripPage() {
   	 	totalRecord = tripService.getTotalRecord((HashMap<String, Object>) map);
	
        if(totalRecord%pageSize==0){
            totalPage = totalRecord / pageSize;
        }else {
            totalPage = (totalRecord / pageSize) + 1;
        }
        
        return totalPage;
   }

    //keyword(검색한 단어) pageNum(페이징에 처리) region(지역), orderColumn(정렬기준)
    public List<TripVO> getSearchedTrip(String keyword, int regionInt, int pageNum, String orderColumn){

    	//int region을 null로 치환하기 위한 동작
    	String region = null;
    	if (regionInt != 0) {
    		region = regionInt+"";
    	}
    	
    	//plan과 trip의 다른 컬럼이름을 통일된 정렬기준으로 맞추기 위한 동작
    	if(orderColumn == null || orderColumn.equals("") || orderColumn.equals("date")) {
    		orderColumn = "writedate";
    	}else if(orderColumn.equals("liked")) {
    		orderColumn = "trip_liked";
    	}else if(orderColumn.equals("hit")) {
    		orderColumn = "hit";
    	}

        map.put("keyword", keyword);
        map.put("region", region);
        map.put("orderColumn", orderColumn);

        //페이지에 따라 값을 가져오기 위한 로직
        int start = (pageNum-1)*pageSize + 1;
        int end = start +  pageSize - 1;

        map.put("start", start);
        map.put("end", end);


        //데이터 테이블에 없는 문자 지역명을 받기 위한 로직
        List<TripVO> prelist = tripService.findAll((HashMap<String, Object>) map);
        List<TripVO> list = new ArrayList<>();
        for (TripVO tripVO : prelist) {
			switch(tripVO.getKorea_code()) {
				case 1: tripVO.setRegion("서울"); break;
				case 2: tripVO.setRegion("경기"); break;
				case 3: tripVO.setRegion("인천"); break;
				case 4: tripVO.setRegion("강원"); break;
				case 5: tripVO.setRegion("충남"); break;
				case 6: tripVO.setRegion("세종"); break;
				case 7: tripVO.setRegion("대전"); break;
				case 8: tripVO.setRegion("충북"); break;
				case 9: tripVO.setRegion("경북"); break;
				case 10: tripVO.setRegion("대구"); break;
				case 11: tripVO.setRegion("울산"); break;
				case 12: tripVO.setRegion("경남"); break;
				case 13: tripVO.setRegion("부산"); break;
				case 14: tripVO.setRegion("전북"); break;
				case 15: tripVO.setRegion("전남"); break;
				case 16: tripVO.setRegion("광주"); break;
				case 17: tripVO.setRegion("제주"); break;
			}
			list.add(tripVO);
		}
        
        return list;
    }
    
    public List<PlanVO> getSearchedPlan(String keyword, int regionInt, int pageNum, String orderColumn){

    	//int region을 null로 치환하기 위한 동작
    	String region = null;
    	if (regionInt != 0) {
    		region = regionInt+"";
    	}
    	
    	//plan과 trip의 다른 컬럼이름을 통일된 정렬기준으로 맞추기 위한 동작
    	if(orderColumn == null || orderColumn.equals("") || orderColumn.equals("date")) {
    		orderColumn = "plan_date";
    	}else if(orderColumn.equals("liked")) {
    		orderColumn = "plan_liked";
    	}else if(orderColumn.equals("hit")) {
    		orderColumn = "plan_hit";
    	}

        map.put("keyword", keyword);
        map.put("region", region);
        map.put("orderColumn", orderColumn);

        //페이지에 따라 값을 가져오기 위한 로직
        int start = (pageNum-1)*pageSize + 1;
        int end = start +  pageSize - 1;

        map.put("start", start);
        map.put("end", end);


        //데이터 테이블에 없는 문자 지역명을 받기 위한 로직
        List<PlanVO> prelist = planService.findAll((HashMap<String, Object>) map);
        List<PlanVO> list = new ArrayList<>();
        for (PlanVO planVO : prelist) {
			switch(planVO.getKorea_code()) {
				case 1: planVO.setPlan_region("서울"); break;
				case 2: planVO.setPlan_region("경기"); break;
				case 3: planVO.setPlan_region("인천"); break;
				case 4: planVO.setPlan_region("강원"); break;
				case 5: planVO.setPlan_region("충남"); break;
				case 6: planVO.setPlan_region("세종"); break;
				case 7: planVO.setPlan_region("대전"); break;
				case 8: planVO.setPlan_region("충북"); break;
				case 9: planVO.setPlan_region("경북"); break;
				case 10: planVO.setPlan_region("대구"); break;
				case 11: planVO.setPlan_region("울산"); break;
				case 12: planVO.setPlan_region("경남"); break;
				case 13: planVO.setPlan_region("부산"); break;
				case 14: planVO.setPlan_region("전북"); break;
				case 15: planVO.setPlan_region("전남"); break;
				case 16: planVO.setPlan_region("광주"); break;
				case 17: planVO.setPlan_region("제주"); break;
			}
			list.add(planVO);
		}
        
        return list;
    }
    
    
}
