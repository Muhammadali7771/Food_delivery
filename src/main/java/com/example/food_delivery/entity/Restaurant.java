package com.example.food_delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUser authUser;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Food> foods;
}
