package com.example.demo.search.controller;

import com.example.demo.search.service.SearchService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Getter @Setter
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

    //keyword(검색한 단어) pageNum(페이징에 처리) column(지역), sortColumn(정렬기준), region(지역)
    @GetMapping("/search/result")
    public void searchResult(String keyword,
                             @RequestParam(value = "column", defaultValue = "0") int column,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             String sortColumn,
                             String region) {
        

    }

}
