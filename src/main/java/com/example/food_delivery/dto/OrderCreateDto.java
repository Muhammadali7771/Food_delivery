package com.example.food_delivery.dto;

import jakarta.validation.constraints.NotBlank;

public record OrderCreateDto(@NotBlank(message = "PhoneNumber can not be blank") String phoneNumber,
                             @NotBlank(message = "FullName can not be blank") String fullName,
                             @NotBlank(message = "Address can not be blank") String address)  {
}