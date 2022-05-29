package com.awbdfirstproject.railwaystationapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 10, nullable = false)
    private String number;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(length = 30, nullable = false)
    private String district;
    @Column(length = 10, nullable = false)
    private String zipcode;

    public Address(String number, String street, String city, String district, String zipcode) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.district = district;
        this.zipcode = zipcode;
    }
}
