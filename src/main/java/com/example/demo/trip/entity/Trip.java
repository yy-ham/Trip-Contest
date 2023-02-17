package com.example.demo.trip.entity;

import com.example.demo.korea.entity.Korea;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Entity
@Data
@Table(name="trip")
public class Trip {
    @Id
    @Column(name = "trip_no")
    private int tripNo;

    @Column(name = "trip_title")
    private String tripTitle;

    @Column(name = "korea_code")
    private int koreaCode;

    @ManyToOne
    @JoinColumn(name = "code")
    private Korea korea;

    @Column(name = "trip_addr")
    private String tripAddr;

    private String lat;

    private String lng;

    @Column(name = "trip_tel")
    private String tripTel;

    @Transient
    private MultipartFile uploadFile;

    @Column(name = "trip_img")
    private String tripImg;

    private String site;

    @Column(name = "trip_detail")
    private String tripDetail;

    private String type;

    private int hit;

    private String opened;

    private String fare;

    private String closed;

    @Column(name = "trip_liked")
    private int tripLiked;

    private String state;

    private Date writedate;
}
