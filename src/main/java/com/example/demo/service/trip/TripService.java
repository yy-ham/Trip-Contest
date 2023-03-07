package com.example.demo.service.trip;

import com.example.demo.dao.trip.TripDAO;
import com.example.demo.db.trip.TripDBManager;
import com.example.demo.entity.trip.Trip;
import com.example.demo.vo.img.ImgVO;
import com.example.demo.vo.trip.TripVO;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@Setter
public class TripService {

    @Autowired
    private TripDAO tripDAO;

    public int getNextTripNo(){
        return tripDAO.getNextTripNo();
    }

    public Trip findByTripNo(int tripNo){
        return tripDAO.findByTripNo(tripNo);
    }

    public Trip save(Trip trip){
        return tripDAO.save(trip);
    }

    public void deleteByTripNo(int tripNo){
        tripDAO.deleteById(tripNo);
    }

    public int getTotalRecord(HashMap<String, Object> map){
        return TripDBManager.getTotalRecord(map);
    }

    public int getTotalPreSavedRecord(String keyword){
        return TripDBManager.getTotalPreSavedRecord(keyword);
    }

    public List<TripVO> findAll(HashMap<String, Object> map){
    	List<TripVO> preTripList = TripDBManager.findAll(map);
    	List<TripVO> tripList = new ArrayList<>();
    	for(TripVO tripVO:preTripList) {
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
    		tripList.add(tripVO);
    	}
    	
        return tripList;
    }

    public List<TripVO> findAllByAdmin(HashMap<String, Object> map){
        return TripDBManager.findAllByAdmin(map);
    }

    public List<ImgVO> findTripImg(int no){
        return TripDBManager.findTripImg(no);
    }

    public int insertTripImg(ImgVO imgVO){
        return TripDBManager.insertTripImg(imgVO);
    }

    public int deleteTripImg(int tripNo){
        return TripDBManager.deleteTripImg(tripNo);
    }
    
    public int deleteTripImgByFname(String fname) {
    	return TripDBManager.deleteTripImgByFname(fname);
    }

    public String getRegionByTripNo(int tripNo) {
    	return TripDBManager.getRegionByTripNo(tripNo);
    }
    
    public int getKoreaCodeByRegion(String region) {
    	return TripDBManager.getKoreaCodeByRegion(region);
    }
    
}
