package com.example.food_delivery.dto.authuser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthUserUpdateDto(@NotBlank(message = "Name cannot be empty")
                                String name,
                                @NotBlank(message = "PhoneNumber must not be blank")
                                @Pattern(
                                        regexp = "^((71|88|9[01349])(\\d{7}))$",
                                        message = "Invalid phone number format"
                                )
                                String phoneNumber,
                                @NotBlank(message = "Password cannot be empty")
                                String password,
                                @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "invalid email")
                                @NotBlank(message = "email can not be blank")
                                String email
) {
}

