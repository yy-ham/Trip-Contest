package com.example.demo.db;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.member.entity.Member;

import jakarta.annotation.Resources;

public class MemberDBManager {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/member/db/sqlMapConfig.xml";
			InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			System.out.println("예외발생: "+e.getMessage());
		}
	}
	//회원가입
		public static int insertMember(Member m) {
			int re = -1;
			//commit해야 함
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.insert("member.insert",m);
			session.close();
			return re;
		}
		//회원정보 수정
		public static int updateMember(Member m) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.update("member.update",m);
			session.close();
			return re;
		}
		//회원탈퇴
		public static int deleteMember(Member m) {
			int re= -1;
			SqlSession session = sqlSessionFactory.openSession();
			re = session.delete("dept.delete",m);
			session.commit();
			session.close();
			return re;
		}
		//id찾기
//		public static Member findByNo(int no) {
//			Member m = null;
//			SqlSession session = sqlSessionFactory.openSession(true);
//			g = session.selectOne("member.findById",no);
//			return g;
//		}
}
