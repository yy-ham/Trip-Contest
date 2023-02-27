package com.example.demo.db.member;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.member.Member;
import com.example.demo.vo.member.MemberVO;

import jakarta.annotation.Resources;

public class MemberDBManager {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/member/sqlMapConfig.xml";
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
//		public static int deleteMember(Member m) {
//			int re= -1;
//			SqlSession session = sqlSessionFactory.openSession();
//			re = session.delete("dept.delete",m);
//			session.commit();
//			session.close();
//			return re;
//		}
		//id찾기
		public static MemberVO findByNameAndPhone(HashMap<String, Object> map) {
			
			SqlSession session = sqlSessionFactory.openSession();
			MemberVO member = session.selectOne("member.findByNameAndPhone",map);
			return member;
		}
		//pw찾기
		public static MemberVO findByIdAndNameAndPhone(HashMap<String, Object> map) {
			SqlSession session = sqlSessionFactory.openSession();
			MemberVO member = session.selectOne("member.findByIdAndNameAndPhone",map);
			return member;
		}
}
