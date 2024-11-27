package com.example.food_delivery.repository;

import com.example.food_delivery.entity.AuthUser;
import com.example.food_delivery.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
