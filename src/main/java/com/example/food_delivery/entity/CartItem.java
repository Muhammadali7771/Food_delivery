package com.example.food_delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Food food;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private double itemPrice;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cart cart;
}