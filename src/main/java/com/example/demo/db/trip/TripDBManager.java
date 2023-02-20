package com.example.demo.db.trip;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.trip.ImgVO;
import com.example.demo.vo.trip.TripVO;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class TripDBManager {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "com/example/demo/db/sqlMapConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory=
                    new SqlSessionFactoryBuilder().build(inputStream);
        }catch (Exception e) {
            System.out.println("예외발생:"+e.getMessage());
        }
    }
    // 여행지 전체 레코드 수
    public static int getTotalRecord(HashMap<String, Object> map){
        SqlSession session = sqlSessionFactory.openSession();
        int totalRecord = session.selectOne("trip.getTotalRecord", map);
        session.close();

        return totalRecord;
    }

    // 임시저장된 여행지 레코드 수 - 관리자용
    public static int getTotalPreSavedRecord(String keyword){
        SqlSession session = sqlSessionFactory.openSession();
        int totalPreSavedRecord = session.selectOne("trip.getTotalPreSavedRecord", keyword);
        session.close();

        return totalPreSavedRecord;
    }

    // 여행지 목록 업데이트순/조회순/좋아요순 출력
    public static List<TripVO> findAll(HashMap<String, Object> map){
        SqlSession session = sqlSessionFactory.openSession();
        List<TripVO> tripVOList = session.selectList("trip.findAll", map);
        session.close();

        return tripVOList;
    }

    // 임시저장된 여행지 목록 업데이트순/조회순/좋아요순 출력 - 관리자용
    public static List<TripVO> findAllByAdmin(HashMap<String, Object> map){
        SqlSession session = sqlSessionFactory.openSession();
        List<TripVO> tripVOListByAdmin = session.selectList("trip.findAllByAdmin", map);
        session.close();

        return tripVOListByAdmin;
    }

    // 대표 이미지 외 나머지 이미지들 가져오기
    public static List<ImgVO> findTripImg(int no){
        SqlSession session = sqlSessionFactory.openSession();
        List<ImgVO> tripImgList = session.selectList("trip.findTripImg", no);
        session.close();

        return tripImgList;
    }

    // 나머지 이미지들 저장
    public static int insert(ImgVO imgVO){
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession(true);
        re = session.insert("trip.insertTripImg", imgVO);
        session.close();

        return re;
    }

    // 나머지 이미지들 삭제
    public static int deleteTripImg(int tripNo){
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession(true);
        re = session.delete("trip.deleteTripImg", tripNo);
        session.close();

        return re;
    }


    // 찜하기
    public static int updateTripLiked(int tripNo){
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession(true);
        re = session.update("trip.updateTripLiked", tripNo);
        session.close();

        return re;
    }

    // 찜 취소
    public static int updateTripNoLiked(int tripNo){
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession(true);
        re = session.update("trip.updateTripNoLiked", tripNo);
        session.close();

        return re;
    }
}
