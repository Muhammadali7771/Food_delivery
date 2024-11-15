package com.example.food_delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(nullable = false)
    private Integer quantity;

    private double itemPrice;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
