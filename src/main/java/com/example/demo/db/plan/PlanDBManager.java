package com.example.demo.db.plan;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.plan.Plan;
import com.example.demo.vo.plan.PlanVO;
import com.example.demo.vo.trip.TripVO;
@Repository
public class PlanDBManager {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/dbConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch (Exception e) {
			System.out.println("error:" + e.getMessage());
		}
	}
	
	//여행계획 목록
	public static List<Plan> findAll(HashMap<String, Object> map){
		List<Plan> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("plan.findAll", map);
		session.close();
		return list;
	}

	
	//여행계획 목록 전체 레코드 수 가져오기
	public static int getTotalRecord(HashMap<String, Object> map) {
		int total = -1;
		SqlSession session = sqlSessionFactory.openSession();
		total = session.selectOne("plan.getTotalRecord", map);
		session.close();
		return total;
	}
	
	//여행계획글 다음 번호 가져오기
	public static int getNextNo() {
		int no = -1;
		SqlSession session = sqlSessionFactory.openSession();
		no = session.selectOne("plan.NextNo");
		session.close();
		return no;
	}
	
	
	//여행일수 계산
	public static int countDaysByPlanNo(int plan_no) {
		int cnt = -1;
		SqlSession session = sqlSessionFactory.openSession();
		cnt = session.selectOne("plan.countDays");
		session.close();
		return cnt;
	}
	



	public static int insertPlan(PlanVO plan) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("plan.insertPlan", plan);
		session.commit();
		session.close();
		return re;
	}
	
	public static int updatePlan(PlanVO p) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.update("plan.updatePlan", p);
		session.commit();
		session.close();
		return re;
	}
	
	public static int countDaysInInsert(HashMap<String, Object> map) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.selectOne("plan.countDaysInInsert", map);
		session.close();
		return re;
	}
	
	public static int getTotalRecordInInsert(int region) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.selectOne("plan.getTotalRecordInInsert", region);
		session.close();
		return re;
	}
	
	public static List<TripVO> findAllInInsert(HashMap<String, Object> map){
		List<TripVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("plan.findAllInInsert", map);
		session.close();
		return list;
	}


	
}
