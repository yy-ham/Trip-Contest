package com.example.demo.search.service;

import com.example.demo.trip.service.TripService;
import com.example.demo.trip.vo.TripVO;
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

    //keyword(검색한 단어) pageNum(페이징에 처리) column(지역), sortColumn(정렬기준), region(지역)
    public List getSearchedTrip(String keyword, int region, int pageNum, String orderColumn){


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
}
