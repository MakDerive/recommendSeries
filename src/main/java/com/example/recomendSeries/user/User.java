package com.example.recomendSeries.user;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.example.recomendSeries.series.Series;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    
    @Column(nullable = false)
    private String password;
    
    @ManyToMany
    @JoinTable(
        name = "user_liked_series",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "series_id")
    )
    private List<Series> likedSeries = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "user_disliked_series",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "series_id")
    )
    private List<Series> dislikedSeries = new ArrayList<>();
}