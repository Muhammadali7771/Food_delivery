package com.example.food_delivery.dto.authuser;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "Email must not be blank")
        String email,
        @NotBlank(message = "password must not be blank")
        String password
) {
}
