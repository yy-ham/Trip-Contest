package com.example.demo.controller.trip;

import com.example.demo.entity.trip.Trip;
import com.example.demo.service.trip.TripService;
import com.example.demo.vo.img.ImgVO;
import com.example.demo.vo.korea.KoreaVO;

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
        
        // 다중 파일 업로드
        List<MultipartFile> fileList = mtfRequest.getFiles("uploadFile");
//        String path = request.getServletContext().getRealPath("/images");
        String path = "/Users/soorin/git/FinalProject/src/main/resources/static/images";
        System.out.println("path:"+path);
        String fname = "";
        List<String> fnameList = new ArrayList<>();
        System.out.println("fileList:"+fileList);
        for(MultipartFile uploadFile : fileList) {
        	fname = uploadFile.getOriginalFilename();
        	System.out.println("orginalFname:"+fname);
        	fnameList.add(fname);
        	
        	String safeFile = path + "/" +fname;
        	System.out.println("safeFile: "+safeFile);
        	try {
				uploadFile.transferTo(new File(safeFile));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        trip.setTripImg(fnameList.get(0));
        System.out.println("첫번째 사진 이름: "+fnameList.get(0));
        System.out.println("다음 사진 이름: "+fnameList.get(1));
        if(fnameList.size()>1) {
        	for(int i=1; i<fnameList.size(); i++) {
	        	ImgVO imgVO = new ImgVO();
	        	imgVO.setFname(fnameList.get(i));
	        	System.out.println("fnameList:"+fnameList.get(i));
	        	imgVO.setNo(tripNo);
	        	imgVO.setType("trip");
	        	tripService.insertTripImg(imgVO);
	        }
        }
        
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
    	String region = "";
    	region = tripService.getRegionByTripNo(tripNo);
    	mav.addObject("trip",trip);
    	mav.addObject("region",region);
    	
    	// 다중 파일
    	List<ImgVO> imgList = tripService.findTripImg(tripNo);
    	mav.addObject("imgList",imgList);
    	
    	return mav;
    }
    
    @GetMapping("/trip/tripUpdate/{tripNo}")
    public ModelAndView updateForm(@PathVariable int tripNo) {
    	ModelAndView mav = new ModelAndView("/trip/tripUpdate");
    	mav.addObject("trip", tripService.findByTripNo(tripNo));
    	
    	return mav;
    }
    
    @PostMapping("/trip/tripUpdate")
    public ModelAndView updateSubmit(Trip trip, MultipartHttpServletRequest mtfRequest) {
    	ModelAndView mav = new ModelAndView();
    	
    	return mav;
    }
    
}
