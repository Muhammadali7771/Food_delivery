package com.example.food_delivery.repository;

import com.example.food_delivery.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
    Optional<CartItem> findByCartIdAndFoodId(Integer cartId, Integer foodId);
}
