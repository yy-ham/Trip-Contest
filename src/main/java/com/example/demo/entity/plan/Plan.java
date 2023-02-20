package com.example.demo.entity.plan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.entity.plandetail.PlanDetail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "plan")
public class Plan {
	@Id
	@Column(name = "plan_no")
	private int planNo;
	
	private String member_id;
	private String plan_title;
	private Date plan_date; //작성일
	private Date plan_start;
	private Date plan_end;
	private int plan_hit;
	private int plan_liked;
	private String plan_img;
	private int korea_code;
	
}