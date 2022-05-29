package com.awbdfirstproject.railwaystationapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "incident")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 1000, nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    private IncidentStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "journey_id")
    private Journey journey;
}
