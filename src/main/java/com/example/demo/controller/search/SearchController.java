package com.example.demo.controller.search;

import com.example.demo.service.search.SearchService;
import com.example.demo.vo.plan.PlanVO;
import com.example.demo.vo.trip.TripVO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Getter @Setter
@Slf4j
public class SearchController {
    
    @Autowired
    private final SearchService searchService;
    
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    // PlanService di주입

    // TripService di주입

    @GetMapping("/search")
    public String searchForm(){
        return "search/searchForm";
    }

    //keyword(검색한 단어) pageNum(페이징에 처리) column(지역), sortColumn(정렬기준)
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

        mav.addObject("planList",searchService.getSearchedPlan(keyword, region, pageNum, orderColumn));
        mav.addObject("tripList",searchService.getSearchedTrip(keyword, region, pageNum, orderColumn));

        return mav;
    }

}
