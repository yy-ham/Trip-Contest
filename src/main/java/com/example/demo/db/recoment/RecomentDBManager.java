package com.example.demo.db.recoment;

import java.io.InputStream;
import java.util.HashMap;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.plan.Plan;
import com.example.demo.vo.recoment.RecomentVO;

public class RecomentDBManager {
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
	
	public static int insertRecoment(RecomentVO r) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("recoment.insertRecoment", r);
		session.commit();
		session.close();
		return re;
	}
	
	public static int updateRecoment(HashMap<String, Object> map) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.update("recoment.updateRecoment", map);
		session.commit();
		session.close();
		return re;
	}
}
