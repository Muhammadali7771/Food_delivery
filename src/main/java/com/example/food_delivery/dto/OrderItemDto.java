package com.example.food_delivery.dto;

import com.example.food_delivery.dto.food.FoodDto;
public record OrderItemDto(FoodDto food, Integer quantity, double itemPrice) {
}