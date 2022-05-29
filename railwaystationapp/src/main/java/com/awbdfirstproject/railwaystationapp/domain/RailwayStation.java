package com.awbdfirstproject.railwaystationapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "railway_station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RailwayStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private RailwayType railwayType;
    @OneToMany(mappedBy = "arrivalRailwayStation")
    private List<Journey> arrivalJourneys;
    @OneToMany(mappedBy = "departureRailwayStation")
    private List<Journey> departureJourneys;

    @OneToOne
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    public RailwayStation(String name, RailwayType railwayType, Address address) {
        this.name = name;
        this.railwayType = railwayType;
        this.address = address;
    }
}

