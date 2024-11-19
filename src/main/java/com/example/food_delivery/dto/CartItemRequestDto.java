package com.example.food_delivery.dto;

import jakarta.validation.constraints.Min;

public record CartItemRequestDto(
        Integer foodId,
        @Min(value = 1, message = "quantity can not be negative or 0")
        Integer quantity) {
}
