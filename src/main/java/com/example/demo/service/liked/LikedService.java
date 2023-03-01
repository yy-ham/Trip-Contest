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
	
	public Optional<Liked> findByNoAndTypeAndMemberId(int no, String type, String memberId) {
		return likedDAO.findByNoAndTypeAndMemberId(no, type, memberId);
	}
	
	public List<Liked> findByMemberIdAndType(String memberId, String type){
		return likedDAO.findByMemberIdAndType(memberId, type);
	}
	
	public int deleteLiked(int no, String member_id) {
		return likedDAO.deleteLiked(no, member_id);
	}
}