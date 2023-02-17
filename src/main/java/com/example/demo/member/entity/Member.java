package com.example.demo.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member{
	@Id
	private String id;
	
	private String pwd;
	private String jumin;
	private String name;
	private String addr;
	private String tel;
	private String mail;
	private String genger;
	private String grade;
	private String member_img;
}
