package com.example.food_delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUser authUser;
    private LocalDateTime orderDateTime;
    private double totalAmount;
    @Column(nullable = false)
    private String address;
}
