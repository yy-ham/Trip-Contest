package com.example.demo.entity.main;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data

public class MainPage {
	@Id
	private int plan_no;
	private String member_id;
	private String plan_title;
	private int plan_hit;
	private int plan_liked;
	private String plan_img;
	private String state;
	private Date plan_date;
	private Date plan_start;
	private Date plan_end;
	
	private int tirp_no;
	private String trip_title;
	private String trip_img;
	private String trip_detail;
	private int hit;
	private int trip_liked;
}
