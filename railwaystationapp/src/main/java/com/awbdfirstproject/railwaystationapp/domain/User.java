package com.awbdfirstproject.railwaystationapp.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private Double balance;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "user")
    private List<Incident> incidents;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    private Boolean accountNotExpired = true;

    @Builder.Default
    private Boolean accountNotLocked = true;

    @Builder.Default
    private Boolean credentialsNotExpired = true;

    public User(String username, String email, String password, String firstname, String lastname, UserType userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userType = userType;
        this.balance = 0.0;
        this.enabled = true;
        this.accountNotExpired = true;
        this.accountNotLocked = true;
        this.credentialsNotExpired = true;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", balance=" + balance +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", roles=" + roles +
                ", enabled=" + enabled +
                ", accountNotExpired=" + accountNotExpired +
                ", accountNotLocked=" + accountNotLocked +
                ", credentialsNotExpired=" + credentialsNotExpired +
                '}';
    }
}
