package com.example.demo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PlanVo {
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

}
