package com.example.demo.entity.days;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "day")
public class Days {
	@Id
	private int day_no;
	
	@Column(name = "plan_no")
	private int planNo;
	
	private int day;
	
	private int plan_1;
	private int plan_2;
	private int plan_3;
	private int plan_4;
	private int plan_5;
}