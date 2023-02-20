package com.example.demo.service.search;

import com.example.demo.service.plan.PlanService;
import com.example.demo.service.trip.TripService;
import com.example.demo.vo.plan.PlanVO;
import com.example.demo.vo.trip.TripVO;

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

    private final int pageSize = 4;
    private int totalRecord = 0;
    private int totalPage = 1;
    private final int pageGroup = 5;
    private final Map<String,Object> map = new HashMap<>();

//    @Autowired
    // 전체 레코드 가져올 planDAO

    @Autowired
    private TripService tripService;
    
    @Autowired
    private PlanService planService;

    //keyword(검색한 단어) pageNum(페이징에 처리) region(지역), orderColumn(정렬기준)
    public List getSearchedTrip(String keyword, int regionInt, int pageNum, String orderColumn){

    	String region = null;
    	if (regionInt != 0) {
    		region = regionInt+"";
    	}

        map.put("keyword", keyword);
        map.put("region", region);

        totalRecord = tripService.getTotal((HashMap<String, Object>) map);

        map.put("orderColumn", orderColumn);


        if(totalRecord%pageSize==0){
            totalPage = totalRecord / pageSize;
        }else {
            totalPage = (totalRecord / pageSize) + 1;
        }

        int start = (pageNum-1)*pageSize + 1;
        int end = start +  pageSize - 1;

        map.put("start", start);
        map.put("end", end);


        //dao 통해 가져온 값을 넣는 리스트
        List<TripVO> list = tripService.findAll(map);
        //임시 리스트
        return list;
    }
    
    public List getSearchedPlan(String keyword, int region, int pageNum, String orderColumn){


        map.put("keyword", keyword);
        map.put("region", region);

        totalRecord = planService.getTotalRecord((HashMap<String, Object>) map);

        map.put("orderColumn", orderColumn);


        if(totalRecord%pageSize==0){
            totalPage = totalRecord / pageSize;
        }else {
            totalPage = (totalRecord / pageSize) + 1;
        }

        int start = (pageNum-1)*pageSize + 1;
        int end = start +  pageSize - 1;

        map.put("start", start);
        map.put("end", end);


        //dao 통해 가져온 값을 넣는 리스트
        List<PlanVO> list = planService.findAll((HashMap<String, Object>) map);
        //임시 리스트
        return list;
    }
    
    
}
