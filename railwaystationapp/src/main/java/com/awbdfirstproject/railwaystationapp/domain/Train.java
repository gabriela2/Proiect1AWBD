package com.awbdfirstproject.railwaystationapp.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "train")
@Data
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "code", nullable = false, length = 20, unique = true)
    private String code;
    @Column(name = "number_of_seats", nullable = false)
    @Min(value = 1)
    private int numberOfSeats;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private TrainType trainType;
    @OneToMany(mappedBy = "train")
    private List<Journey> journeys;
}
