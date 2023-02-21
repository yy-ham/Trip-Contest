package com.example.demo.controller.trip;

import com.example.demo.entity.trip.Trip;
import com.example.demo.service.trip.TripService;
import com.example.demo.vo.img.ImgVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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

    @GetMapping("/trip/tripInsert")
    public ModelAndView insertForm(){
        ModelAndView mav = new ModelAndView("/trip/tripInsert");
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
    	Trip trip = tripService.findByTripNo(tripNo);
    	mav.addObject("trip", trip);
    	String region = "";
    	region = tripService.getRegionByTripNo(tripNo);
    	mav.addObject("region",region);
//    	int koreaCode = trip.getKorea().getCode();
//    	mav.addObject("koreaCode", koreaCode);
    	
    	List<ImgVO> imgList = tripService.findTripImg(tripNo);
    	mav.addObject("imgList",imgList);
    	
    	return mav;
    }
    
    @PostMapping("/trip/tripUpdate")
    public ModelAndView updateSubmit(Trip trip, MultipartHttpServletRequest mtfRequest) {
    	int tripNo = trip.getTripNo();
    	Trip oldTrip = tripService.findByTripNo(tripNo);
    	ModelAndView mav = new ModelAndView("redirect:/trip/tripDetail/{tripNo}");
        trip.setTripNo(tripNo);
        trip.setLat(oldTrip.getLat());
        trip.setLng(oldTrip.getLng());
        trip.setType(oldTrip.getType());
        trip.setHit(oldTrip.getHit());
        trip.setTripLiked(oldTrip.getTripLiked());
        trip.setState("Y");
        trip.setKorea(oldTrip.getKorea());
        trip.setWritedate(LocalDateTime.now());
       
        //        String path = request.getServletContext().getRealPath("/images");
        String path = "/Users/soorin/git/FinalProject/src/main/resources/static/images";
        System.out.println("path:"+path);

        // trip에 있는 이미지 파일명
        String oldFname = oldTrip.getTripImg();
        System.out.println("oldFname:"+oldFname);
        
        // 그 외의 이미지들
        List<ImgVO> oldImgList = tripService.findTripImg(tripNo);
        List<String> oldImgFnameList = new ArrayList<>(); // 원래 있던 이미지 파일명 리스트
        if(oldImgList != null) {
        	for(ImgVO imgVO: oldImgList) {
	        	String oldImgFname = imgVO.getFname();
	        	oldImgFnameList.add(oldImgFname);
	        }
        }
        
        String fname = "";       
        // 다중 파일 업로드
        List<MultipartFile> fileList = mtfRequest.getFiles("uploadFile");
        for (MultipartFile multipartFile : fileList) {
			System.out.println(multipartFile.getOriginalFilename());
		}
        
        List<String> fnameList = new ArrayList<>();
        if(fileList.size() != 1 || !fileList.get(0).getOriginalFilename().equals("")) {
        	// 삭제해야할 원래 있던 이미지들
            File file = new File(path+"/"+oldFname);
    		file.delete();
    		
    		for(int i=0; i<oldImgFnameList.size(); i++) {
	        	ImgVO imgVO = oldImgList.get(i);
	        	String oldImgFname = imgVO.getFname();
	        	File file2 = new File(path+"/"+oldImgFname);
	        	file2.delete();
	        	tripService.deleteTripImgByFname(oldImgFname);
	        }
    		System.out.println("수정 전 이미지 삭제!");
        	
        	System.out.println("fileList:"+fileList.toString());
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
            System.out.println("수정, 첫번째 사진 이름: "+fnameList.get(0));
            if(fnameList.size()>1) {
            	for(int i=1; i<fnameList.size(); i++) {
    	        	ImgVO imgVO = new ImgVO();
    	        	imgVO.setFname(fnameList.get(i));
    	        	System.out.println("수정, fnameList:"+fnameList.get(i));
    	        	imgVO.setNo(tripNo);
    	        	imgVO.setType("trip");
    	        	tripService.insertTripImg(imgVO);
    	        }
            }
            
        }else {
        	trip.setTripImg(oldFname);
        	
        }
        
    	Trip checkTrip = tripService.save(trip);
    	String msg = "";
        if(checkTrip != null){
            msg = "수정되었습니다!";
            
        }else{
            msg = "수정실패!";
        }
        System.out.println(msg);
        mav.addObject("tripNo", tripNo);
    	
    	return mav;
    }
    
    @GetMapping("/trip/tripDelete/{tripNo}")
    public ModelAndView deleteTrip(@PathVariable int tripNo) {
    	ModelAndView mav = new ModelAndView("redirect:/trip/tripList");
    	Trip trip = tripService.findByTripNo(tripNo);
    	
        String path = "/Users/soorin/git/FinalProject/src/main/resources/static/images";
        String oldFname = trip.getTripImg();
        List<ImgVO> oldImgList = tripService.findTripImg(tripNo);
    	
    	tripService.deleteByTripNo(tripNo);
    	int re = tripService.deleteTripImg(tripNo);
    	if(re > 0) {
    		File file = new File(path+"/"+oldFname);
    		file.delete();
    		
    		for(int i=0; i<oldImgList.size(); i++) {
	        	ImgVO imgVO = oldImgList.get(i);
	        	String oldImgFname = imgVO.getFname();
	        	File file2 = new File(path+"/"+oldImgFname);
	        	file2.delete();
	        }
    		System.out.println("이미지 삭제!");
    	}
    	
    	return mav;
    }
}
