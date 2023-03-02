package com.example.demo.db.plandetail;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.plan.Plan;
import com.example.demo.entity.plandetail.PlanDetail;
import com.example.demo.entity.trip.Trip;
import com.example.demo.vo.plandetail.PlanDetailVO;
import com.example.demo.vo.trip.TripVO;

public class PlanDetailDBManager {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch (Exception e) {
			System.out.println("error:" + e.getMessage());
		}
	}
	
	//날짜별 일정 등록
	public static int insertDays(PlanDetail d){
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("days.insertDays", d);
		session.close();
		return re;
	}
	
	//날짜별 일정 수정
	public static int updatePlanDetail(PlanDetail d){
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("plandetail.updatePlanDetail", d);
		session.commit();
		session.close();
		return re;
	}
	
	public static List<TripVO> getPlanDetail(HashMap<String, Object> map){
		List<TripVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("plandetail.getPlanDetail", map);
		session.close();
		return list;
	}
	
	public static String findTripImg(int trip_no) {
		String tripImg = "";
		SqlSession session = sqlSessionFactory.openSession();
		tripImg = session.selectOne("plandetail.findTripImg", trip_no);
		session.close();
		return tripImg;
	}
	
	public static List<Integer> getPdNo(int plan_no){
		List<Integer> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("plandetail.getPdNo", plan_no);
		session.close();
		return list;
	}
	
}
