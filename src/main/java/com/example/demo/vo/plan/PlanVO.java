package com.example.demo.vo.plan;

import java.util.Date;

import lombok.Data;

@Data
public class PlanVO {
	private int plan_no;
	private String member_id;
	private String plan_title;
	private String plan_date; //작성일
	private String plan_start;
	private String plan_end;
	private int plan_hit;
	private int plan_liked;
	private String plan_img;
	private int korea_code;
}
