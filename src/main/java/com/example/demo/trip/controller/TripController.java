package com.example.demo.trip.controller;

import com.example.demo.trip.entity.Trip;
import com.example.demo.trip.service.TripService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Setter
public class TripController {

    public int pageSIZE = 8;
    public int totalRecord = 0;
    public int totalPage = 1;

    @Autowired
    private TripService tripService;

    @GetMapping("/trip/tripInsert/{tripNo}")
    public ModelAndView insertForm(@PathVariable int tripNo){
        ModelAndView mav = new ModelAndView("/trip/tripInsert");
        mav.addObject("tripNo", tripNo);
        return mav;
    }

    @PostMapping("/trip/tripInsert")
    public ModelAndView insertSubmit(Trip trip){
        ModelAndView mav = new ModelAndView();
        int tripNo = tripService.getNextTripNo();
        Trip checkTrip = tripService.save(trip);
        String msg = "";
        if(checkTrip != null){
            msg = "등록되었습니다.!";
        }else{
            msg = "등록실패!";
        }
        System.out.println(msg);
        return mav;
    }

}
