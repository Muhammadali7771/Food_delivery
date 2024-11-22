package com.example.food_delivery.dto.authuser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthUserUpdateDto(@NotBlank(message = "Name cannot be empty")
                                String name,
                                @NotBlank(message = "Phone number cannot be empty")
                                String phoneNumber,
                                @NotBlank(message = "Password cannot be empty")
                                String password,
                                @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "invalid email")
                                @NotBlank(message = "email can not be blank")
                                String email
) {
}

