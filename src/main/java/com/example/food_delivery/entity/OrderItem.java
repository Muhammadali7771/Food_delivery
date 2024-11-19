package com.example.food_delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    public OrderItem(Food food, Integer quantity, double itemPrice, Order order) {
        this.food = food;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.order = order;
    }

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
