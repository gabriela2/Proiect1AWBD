package com.awbdfirstproject.railwaystationapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "final_price", nullable = false)
    private double finalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "journey_id")
    private Journey journey;

    public Ticket(double finalPrice, User user, Journey journey) {
        this.finalPrice = finalPrice;
        this.user = user;
        this.journey = journey;
    }
}