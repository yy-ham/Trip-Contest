package com.example.demo.service.liked;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.liked.DBManager;
import com.example.demo.entity.liked.Liked;

import lombok.Setter;

@Service
@Setter
public class LikedService {
	
	
	
	public List<Liked> findByIdandPlan(String member_id){
		return DBManager.findByIdandPlan(member_id);
	}
  
	
	public List<Liked> findByIdandTrip(String member_id){
		return DBManager.findByIdandTrip(member_id);
	}
	
	public int deleteLiked(int like_no) {
		return DBManager.deleteLiked(like_no);
	}
}
