package com.example.demo.vo.plan;

import java.util.Date;

import lombok.Data;

@Data
public class PlanVO {
	
	private int plan_no;
	private String member_id;
	private String plan_title;
	private Date plan_date;
	private Date plan_start;
	private Date plan_end;
	private int plan_hit;
	private int plan_liked;
	private String plan_img;
	private String type;
	private int korea_code;
	private String plan_region;

}
