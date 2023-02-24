package com.example.demo.dao.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.trip.Trip;

@Repository
public interface TripDAO extends JpaRepository<Trip, Integer> {

}
