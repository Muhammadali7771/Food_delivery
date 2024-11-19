package com.example.food_delivery.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryUpdateDto(
        @NotBlank(message = "Category can not be blank")
        String name,
        @NotBlank(message = "Description can not be blank")
        String description
) {

}
