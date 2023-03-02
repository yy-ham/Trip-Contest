


package com.example.demo.db.main;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import com.example.demo.vo.plan.PlanVO;
import com.example.demo.vo.trip.TripVO;


public class MainDBManager {
public static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static List<PlanVO> Planerbest(){
		List<PlanVO> planerbest = null;
		SqlSession session = sqlSessionFactory.openSession();
		planerbest = session.selectList("MainPage.Planerbest");
		session.close();
		return planerbest;
	}
	
	public static List<TripVO> Tripbest(){
		List<TripVO> tripbest = null;
		SqlSession session = sqlSessionFactory.openSession();
		tripbest = session.selectList("MainPage.Tripbest");
		session.close();
		return tripbest;
	}
	
		public static List<TripVO> TripSearch(int korea_code){
		List<TripVO> TripSearch = null;
		SqlSession session = sqlSessionFactory.openSession();
		TripSearch = session.selectList("TripSearchPage.TripSearch",korea_code);
		session.close();
		return TripSearch;
	}
		
		public static List<TripVO> PlanSearch(int korea_code){
			List<TripVO> PlanSearch = null;
			SqlSession session = sqlSessionFactory.openSession();
			PlanSearch = session.selectList("TripSearchPage.TripSearch",korea_code);
			session.close();
			return PlanSearch;
		}

}
