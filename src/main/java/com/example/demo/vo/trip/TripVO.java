package com.example.demo.vo.trip;

import java.util.Date;

import lombok.Data;

@Data
public class TripVO {
	private int trip_no;
	
	private String trip_title;
	private int korea_code;
	private String trip_addr;
	private String lat;
	private String lng;
	private String trip_tel;
	private String trip_img;
	private String site;
	private String trip_datail;
	private String type;
	private int hit;
	private String opened;
	private String fare;
	private String closed;
	private int trip_liked;
	private String state;
	private Date writedate;
}
