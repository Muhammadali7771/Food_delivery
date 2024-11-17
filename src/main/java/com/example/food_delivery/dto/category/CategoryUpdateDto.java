package com.example.food_delivery.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryUpdateDto(
        String name,
        String description
) {

}
