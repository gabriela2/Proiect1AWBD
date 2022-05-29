package com.awbdfirstproject.railwaystationapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "journey")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int distance;
    @JoinColumn(nullable = false)
    private Timestamp departureTime;
    @Column(nullable = false)
    private Timestamp arrivalTime;
    private int minuteLate;
    @Column(name = "ticket_price", nullable = false)
    private double ticketPrice;
    @Enumerated(EnumType.STRING)
    private JourneyStatus journeyStatus;
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "departure_railway_station_id")
    private RailwayStation departureRailwayStation;
    @ManyToOne
    @JoinColumn(name = "arrival_railway_station_id")
    private RailwayStation arrivalRailwayStation;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "journey")
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "journey")
    private List<Incident> incidents;

    public Journey(long id, int distance, Timestamp departureTime, Timestamp arrivalTime, int minuteLate, double ticketPrice, JourneyStatus journeyStatus, Train train, RailwayStation departureRailwayStation, RailwayStation arrivalRailwayStation, Company company) {
        this.id = id;
        this.distance = distance;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.minuteLate = minuteLate;
        this.ticketPrice = ticketPrice;
        this.journeyStatus = journeyStatus;
        this.train = train;
        this.departureRailwayStation = departureRailwayStation;
        this.arrivalRailwayStation = arrivalRailwayStation;
        this.company = company;
    }
}
