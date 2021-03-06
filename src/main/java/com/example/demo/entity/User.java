package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "users")
public class User {
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;

    @Email
    @Column(unique=true)
    private String email;

    private String password;

//  Eager Loading is a design pattern in which data initialization occurs on the spot
//  Lazy Loading is a design pattern which is used to defer initialization of an object as long as it's possible
//  Hibernate applies lazy loading approach on entities and associations by providing a proxy implementation of classes.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // add user_countries
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_countries",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries = new HashSet<>();

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
