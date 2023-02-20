package com.example.demo.db.plandetail;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.plandetail.PlanDetail;

public class PlanDetailDBManager {
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
	
	//날짜별 일정 등록
	public static int insertDays(PlanDetail d){
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("days.insertDays", d);
		session.close();
		return re;
	}
	
	//날짜별 일정 수정
	public static int updateDays(PlanDetail d){
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("days.updateDays", d);
		session.close();
		return re;
	}
	
		
}
