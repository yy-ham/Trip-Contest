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
@Repository
public class PlanDBManager {
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
	
	//여행계획 목록
	public static List<PlanVO> findAll(HashMap<String, Object> map){
		List<PlanVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("plan.findAll", map);
		session.close();
		return list;
	}
	
	//여행계획 목록 (지역별)
	public static List<Plan> findByRegion(HashMap<String, Object> map){
		List<Plan> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("plan.findByRegion", map);
		session.close();
		return list;
	}
	
	//여행계획 목록 전체 레코드 수 가져오기
	public static int getTotalRecord(HashMap<String, Object> map) {
		int total = -1;
		SqlSession session = sqlSessionFactory.openSession();
		total = session.selectOne("plan.TotalRecord", map);
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
	
	//회원이 찜한 여행계획 목록
	public static List<Plan> likedFindByMemberId(String member_id) {
		List<Plan> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("plan.NextNo", member_id);
		session.close();
		return list;
	}
	
	//여행일수 계산
	public static int countDaysByPlanNo(int plan_no) {
		int cnt = -1;
		SqlSession session = sqlSessionFactory.openSession();
		cnt = session.selectOne("plan.countDays");
		session.close();
		return cnt;
	}
	
	//여행계획 검색
	public static List<Plan> searchPlan(HashMap<String, Object> map){
		List<Plan> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("plan.searchPlan", map);
		session.close();
		return list;
	}

	//ajax?
//	//여행계획 찜하기
//	public static List<Plan> searchPlan(HashMap<String, Object> map){
//		List<Plan> list = null;
//		SqlSession session = sqlSessionFactory.openSession();
//		list = session.selectList("plan.searchPlan", map);
//		session.close();
//		return list;
//	}
//	
//	//여행계획 찜 취소하기
//	public static List<Plan> searchPlan(HashMap<String, Object> map){
//		List<Plan> list = null;
//		SqlSession session = sqlSessionFactory.openSession();
//		list = session.selectList("plan.searchPlan", map);
//		session.close();
//		return list;
//	}

	
}
