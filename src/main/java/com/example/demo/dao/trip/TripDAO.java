package com.example.demo.dao.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.trip.Trip;

import java.util.List;

@Repository
public interface TripDAO extends JpaRepository<Trip, Integer> {

    // 새로운 tripNo 생성
    @Query("select nvl(max(tripNo),0)+1 from Trip")
    int getNextTripNo();

    // 여행지 상세 출력
    Trip findByTripNo(int tripNo);
    
}
