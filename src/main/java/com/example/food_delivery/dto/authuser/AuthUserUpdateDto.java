package com.example.food_delivery.dto.authuser;

public record AuthUserUpdateDto(
        String name,
        String phoneNumber,
        String password
) {
}
