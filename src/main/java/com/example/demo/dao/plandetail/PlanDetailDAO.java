package com.example.demo.dao.plandetail;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.plandetail.PlanDetail;
import com.example.demo.entity.trip.Trip;

import jakarta.transaction.Transactional;

@Repository
public interface PlanDetailDAO extends JpaRepository<PlanDetail, Integer> {

	//여행계획 상세 - 날짜별 일정 조회
	public List<PlanDetail> findByPlanNo(int plan_no);

	@Query(value = "SELECT t.* FROM TRIP t WHERE t.trip_no IN "
			+ "(SELECT trip_no FROM PLANDETAIL pd WHERE pd.plan_no = ?1 AND pd.DAY = ?2)",
			nativeQuery = true)
	public List<Trip> getPlanDetail(int plan_no, int day);
	
	@Modifying
	@Transactional
	@Query(value = "delete plandetail where plan_no = ?", nativeQuery = true)
	public int deletePlanDetail(int plan_no);

	@Modifying
	@Transactional
	@Query(value = "insert into plandetail(pd_no, plan_no, day, trip_no) "
			+ "values(seq_plandetail.nextval, ?1, ?2, ?3)", nativeQuery = true)
	public int insertPlanDetail(int plan_no, int day, int trip_no);
	
	//찜
	
	//찜 등록
	@Modifying
	@Transactional
	@Query(value = "update plan set plan_liked = plan_liked + 1 where plan_no = ?", nativeQuery = true)
	public int addLiked(int plan_no);
	
	@Modifying
	@Transactional
	@Query(value = "insert into liked(like_no, member_id, no, type, like_img, liked_title) "
			+ "values(seq_liked.nextval, ?1, ?2, 'plan', (select trip_img from trip where trip_no = ?3), ?4)", nativeQuery = true)
	public int insertLiked(String member_id, int no, int trip_no, String liked_title);
	
	
	//찜 삭제
	@Modifying
	@Transactional
	@Query(value = "update plan set plan_liked = plan_liked - 1 where plan_no = ?", nativeQuery = true)
	public int cancelLiked(int plan_no);
	
	@Modifying
	@Transactional
	@Query(value = "delete liked where no =? and type = 'plan'", nativeQuery = true)
	public int deleteLiked(int no);
	
	
	
	@Query(value = "select plan_liked from plan where plan_no = ?", nativeQuery = true)
	public int getLiked(int plan_no);
	
	@Query(value = "select count(*) from liked where no = ?1 and type = 'plan' and member_id = ?2",
			nativeQuery = true)
	public int getUserLiked(int no, String member_id);
	
	//여행계획 등록할 때 trip_no로 이미지 제목 가져오기
	@Query(value = "select trip_img from trip where trip_no = ?", nativeQuery = true)
	public String getImg(int trip_no);
	
}