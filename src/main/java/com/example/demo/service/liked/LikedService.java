package com.example.demo.service.liked;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.liked.LikedDAO;
import com.example.demo.entity.liked.Liked;
import lombok.Setter;

@Service
@Setter
public class LikedService {
	
	@Autowired
	private LikedDAO likedDAO;
	
	public Liked save(Liked liked){
		return likedDAO.save(liked);
	}
	
	public void delete(Liked liked) {
		likedDAO.delete(liked);
	}
	
	public Optional<Liked> findByLikeNo(int likeNo) {
		return likedDAO.findById(likeNo);
	}
	
	public List<Liked> findByMemberId(String memberId){
		return likedDAO.findByMemberId(memberId);
	}
}
