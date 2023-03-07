package com.example.demo.entity.liked;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@SequenceGenerator(
	name = "SEQ_GENERATOR",
	sequenceName = "seq_liked",

	initialValue = 1, //시작값
    allocationSize = 1 //메모리를 통해 할당할 범위 사이즈
)
@Table(name = "liked")
public class Liked {
	@Id
	@Column(name = "like_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENERATOR")
	private int likeNo;
	@Column(name = "member_id")
	private String memberId;
	private int no;
	private String type;
	@Column(name = "like_img")
	private String likeImg;
	@Column(name = "liked_title")
	private String likedTitle;
}

