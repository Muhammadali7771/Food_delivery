package com.example.food_delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record OrderCreateDto(  @NotBlank(message = "PhoneNumber must not be blank")
                               @Pattern(
                                       regexp = "^((71|88|9[01349])(\\d{7}))$",
                                       message = "Invalid phone number format")String phoneNumber,
                             @NotBlank(message = "FullName can not be blank") String fullName,
                             @NotBlank(message = "Address can not be blank") String address)  {
}