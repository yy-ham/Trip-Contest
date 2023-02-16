package com.example.demo.korea.entity;

import com.example.demo.trip.entity.Trip;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="korea")
public class Korea {
    @Id
    private int code;

    private String region;

    @OneToMany(mappedBy = "korea")
    private List<Trip> tripList = new ArrayList<>();
}
