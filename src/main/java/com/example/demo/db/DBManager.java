


package com.example.demo.db;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.PlanVo;
import com.example.demo.vo.TripVo;


public class DBManager {
public static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "com/example/demo/db/dbConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static List<PlanVo> Planerbest(){
		List<PlanVo> planerbest = null;
		SqlSession session = sqlSessionFactory.openSession();
		planerbest = session.selectList("MainPage.Planerbest");
		session.close();
		return planerbest;
	}
	
	public static List<TripVo> Tripbest(){
		List<TripVo> tripbest = null;
		SqlSession session = sqlSessionFactory.openSession();
		tripbest = session.selectList("MainPage.Tripbest");
		session.close();
		return tripbest;
	}
	
		public static List<TripVo> TripSearch(int korea_code){
		List<TripVo> TripSearch = null;
		SqlSession session = sqlSessionFactory.openSession();
		TripSearch = session.selectList("TripSearchPage.TripSearch",korea_code);
		session.close();
		return TripSearch;
	}
		
		public static List<TripVo> PlanSearch(int korea_code){
			List<TripVo> PlanSearch = null;
			SqlSession session = sqlSessionFactory.openSession();
			PlanSearch = session.selectList("TripSearchPage.TripSearch",korea_code);
			session.close();
			return PlanSearch;
		}

}
