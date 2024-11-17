package com.example.food_delivery.dto;

import com.example.food_delivery.dto.food.FoodDto;

public record CartItemDto(FoodDto food,
                          Integer quantity,
                          double itemPrice) {
}
