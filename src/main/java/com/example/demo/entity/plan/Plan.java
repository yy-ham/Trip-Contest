package com.example.demo.entity.plan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.entity.plandetail.PlanDetail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "plan")
public class Plan {
	@Id
	@Column(name = "plan_no")
	private int planNo;
	@Column(name = "member_id")
	private String memberId;
	@Column(name = "plan_title")
	private String planTitle;
	@Column(name = "plan_date")
	private Date planDate; //작성일
	@Column(name = "plan_start")
	private Date planStart;
	@Column(name = "plan_end")
	private Date planEnd;
	@Column(name = "plan_hit")
	private int planHit;
	@Column(name = "plan_liked")
	private int planLiked;
	@Column(name = "plan_img")
	private String planImg;
	@Column(name = "korea_code")
	private int koreaCode;
	
}