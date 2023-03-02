package com.example.demo.entity.plandetail;

import com.example.demo.entity.plan.Plan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "plandetail")
public class PlanDetail {
	@Id
	private int pd_no;
	
	@Column(name = "plan_no")
	private int planNo;
	
//	@ManyToOne
//	@JoinColumn(name = "plan_no", insertable = true, updatable = true)
//	private Plan plan;

	private int day;
	
	@Column(name = "trip_no")
	private int tripNo;
}