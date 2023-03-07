package com.example.demo.controller.search;

import com.example.demo.service.search.SearchService;
import com.example.demo.vo.plan.PlanVO;
import com.example.demo.vo.trip.TripVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Getter @Setter
@Slf4j
public class SearchController {
    
    @Autowired
    private final SearchService searchService;
    
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    //검색결과 페이지로 이동하는 메소드
    @GetMapping("/search/result")
    public ModelAndView searchResult(String keyword,
                             @RequestParam(value = "region", defaultValue = "0") int region,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             String orderColumn
                            ) {


        ModelAndView mav = new ModelAndView();
        mav.setViewName("/search/searchResult");
        
        log.info("keyword ={}",keyword);
        log.info("region ={}",region);
        log.info("pageNum ={}",pageNum);
        log.info("orderColumn ={}",orderColumn);
       
        //검색결과를 상태유지
        mav.addObject("keyword", keyword);
        mav.addObject("region", region);
        mav.addObject("pageNum", pageNum);
        mav.addObject("orderColumn", orderColumn);

        //검색된 결과를 model에 담는것
        mav.addObject("planList",searchService.getSearchedPlan(keyword, region, pageNum, orderColumn));   
        mav.addObject("tripList",searchService.getSearchedTrip(keyword, region, pageNum, orderColumn));
        
        //검색결과의 전체 갯수를 구하는 것
        searchService.getTotalPlanPage();
        int tripRecord = searchService.getTotalRecord();
        searchService.getTotalTripPage();
        int planRecord = searchService.getTotalRecord();
        
        mav.addObject("totalRecord",planRecord+tripRecord );
        

        return mav;
    }
    

}
