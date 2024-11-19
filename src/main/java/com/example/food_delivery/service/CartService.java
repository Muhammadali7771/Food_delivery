package com.example.food_delivery.service;

import com.example.food_delivery.dto.CartDto;
import com.example.food_delivery.dto.CartItemRequestDto;
import com.example.food_delivery.dto.CartItemUpdateDto;

public interface CartService {
    CartDto getMyCart();

    void addProductToCart(CartItemRequestDto dto);

    void updateQuantity(CartItemUpdateDto dto);

    void removeProductFromCart(Integer foodId);
}
