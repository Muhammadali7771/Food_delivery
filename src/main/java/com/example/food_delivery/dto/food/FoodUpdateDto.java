package com.example.food_delivery.dto.food;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record FoodUpdateDto(
        @NotBlank(message = "name cannot must not be blank")
        String name,
        @NotBlank(message = "description must not be blank")
        String description,
        @Min(value = 0, message = "price cannot be negative")
        double price
) {
}
