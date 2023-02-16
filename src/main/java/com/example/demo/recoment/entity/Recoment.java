package com.example.demo.recoment.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "recoment")
public class Recoment {
	@Id
	private int rec_no;
	
	private String member_id;
	private String rec_content;
	private int no;
	private String type;
	private Date rec_date;
}
