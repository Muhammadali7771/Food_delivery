package com.example.food_delivery.dto.food;

public record FoodDto(
        Integer id,
        String name,
        double price,
        String description,
        String[] ingredients
) {
}
