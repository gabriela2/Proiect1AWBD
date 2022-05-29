package com.awbdfirstproject.railwaystationapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }
}
