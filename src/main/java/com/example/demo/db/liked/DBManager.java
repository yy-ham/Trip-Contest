package com.example.demo.db.liked;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.liked.Liked;

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
	
	public static List<Liked> findByIdandType(HashMap<String, Object> map){
		List<Liked> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list= session.selectList("liked.findByIdandType", map);
		session.close();
		return list;
	}
	
	public static int deleteLiked(int like_no) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.delete("liked.delete", like_no);
		session.commit();
		session.close();
		return re;
	}
}
