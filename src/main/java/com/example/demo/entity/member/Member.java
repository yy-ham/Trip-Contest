package com.example.demo.entity.member;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
	@Transient
    private MultipartFile uploadFile;
}
