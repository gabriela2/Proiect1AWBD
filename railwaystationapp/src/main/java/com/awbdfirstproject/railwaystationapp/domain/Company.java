package com.awbdfirstproject.railwaystationapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 15, nullable = false, unique = true)
    private String identificationNumber;
    @Lob
    private Byte[] image;
    @OneToMany(mappedBy = "company")
    private List<Journey> journeys;

    public Company() {
    }

    public Company(String name, String identificationNumber) {
        this.name = name;
        this.identificationNumber = identificationNumber;
    }
}
