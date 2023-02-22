package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="member")
public class Member{
	@Id
	private String id;
	
	private String pwd;
	private String jumin;
	private String name;
	private String addr;
	private String phone;
	private String mail;
	private String gender;
	private String grade;
	
	@Column(name = "member_img")
	private String memberImg;
}
