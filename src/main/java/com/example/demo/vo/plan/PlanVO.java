package com.example.demo.vo.plan;



import lombok.Data;

@Data
public class PlanVO {
	
	private int plan_no;
	private String member_id;
	private String plan_title;
	private String plan_date;
	private String plan_start;
	private String plan_end;
	private int plan_hit;
	private int plan_liked;
	private String plan_img;
	private String type;
	private int korea_code;
	private String plan_region;
	private String region;

}
