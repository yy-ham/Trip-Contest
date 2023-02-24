package com.example.demo.entity.korea;

import com.example.demo.entity.trip.*;
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