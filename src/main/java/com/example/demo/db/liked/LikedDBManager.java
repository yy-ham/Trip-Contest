package com.example.demo.db.liked;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.liked.Liked;

public class LikedDBManager {
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
	
	public static List<Liked> findByIdandTrip(String member_id){
		List<Liked> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list= session.selectList("liked.findByIdandTrip", member_id);
		session.close();
		return list;
	}
	
	
	public static List<Liked> findByIdandPlan(String member_id){
		List<Liked> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list= session.selectList("liked.findByIdandPlan", member_id);
		session.close();
		return list;
	}

}
