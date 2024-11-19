package com.example.food_delivery.entity;

import com.example.food_delivery.enums.ORDER_STATUS;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
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
    private String phoneNumber;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String address;
    @Enumerated(EnumType.STRING)
    private ORDER_STATUS status;
}
