package com.example.food_delivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class History {
    @Id
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    private Order order;
    private LocalDateTime deliveredAt;
}
