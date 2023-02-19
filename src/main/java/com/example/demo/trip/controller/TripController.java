package com.example.demo.trip.controller;

import com.example.demo.korea.vo.KoreaVO;
import com.example.demo.trip.entity.Trip;
import com.example.demo.trip.service.TripService;
import com.example.demo.trip.vo.ImgVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
    public ModelAndView insertSubmit(Trip trip, HttpServletRequest request, MultipartHttpServletRequest mtfRequest){
        ModelAndView mav = new ModelAndView("/trip/tripList");
        int tripNo = tripService.getNextTripNo();
        int hit = 0;
        int tripLiked = 0;
        trip.setTripNo(tripNo);
        trip.setLat("11");
        trip.setLng("22");
        trip.setType("trip");
        trip.setHit(hit);
        trip.setTripLiked(tripLiked);
        trip.setState("Y");
        
//        // 다중 파일 업로드
//        List<MultipartFile> fileList = mtfRequest.getFiles("uploadFile");
//        String path = request.getServletContext().getRealPath("/images");
//        System.out.println("path:"+path);
//        String fname = null;
//        List<String> fnameList = new ArrayList<String>();
//        
//        for(MultipartFile uploadFile : fileList) {
//        	fname = uploadFile.getOriginalFilename();
//        	System.out.println("orginalFname:"+fname);
//        	fnameList.add(fname);
//        	trip.setTripImg(fname);
//        	try {
//				uploadFile.transferTo(new File(path+"/"+fname));
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//        trip.setTripImg(fnameList.get(0));
//        for(int i=1; i<=fnameList.size(); i++) {
//        	ImgVO imgVO = new ImgVO();
//        	imgVO.setFname(fnameList.get(i));
//        	tripService.insertTripImg(imgVO);
//        }
        
        
        
        Trip checkTrip = tripService.save(trip);
        String msg = "";
        if(checkTrip != null){
            msg = "등록되었습니다!";
        }else{
            msg = "등록실패!";
        }
        System.out.println(msg);
        return mav;
    }
    
    
    @GetMapping("/trip/tripDetail/{tripNo}")
    public ModelAndView tripDetail(@PathVariable int tripNo) {
    	ModelAndView mav = new ModelAndView("/trip/tripDetail");
    	Trip trip = tripService.findByTripNo(tripNo);
//    	KoreaVO koreaVO = tripService.getRegionByTripNo(tripNo);
//    	String region = "";
//    	region = tripService.getRegionByTripNo(tripNo);
//    	mav.addObject("region",region);
    	mav.addObject("trip",trip);
    	
    	return mav;
    }

}
