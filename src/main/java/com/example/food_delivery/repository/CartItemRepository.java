package com.example.food_delivery.repository;

import com.example.food_delivery.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
}
