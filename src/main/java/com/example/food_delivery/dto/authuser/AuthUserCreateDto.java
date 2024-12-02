package com.example.food_delivery.dto.authuser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record AuthUserCreateDto(
        @NotBlank(message = "name must not be blank")
        String name,
        @NotBlank(message = "email can not be blank")
        @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "invalid email")
        String email,
        @NotBlank(message = "password can not be blank")
        @Size(message = "password should include at least 2 characters", min = 2)
        String password,
        @NotBlank(message = "PhoneNumber must not be blank")
        @Pattern(
                regexp = "^((71|88|9[01349])(\\d{7}))$",
                message = "Invalid phone number format"
        )
        String phoneNumber) {
}
