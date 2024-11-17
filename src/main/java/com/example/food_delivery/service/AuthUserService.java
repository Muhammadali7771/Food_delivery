package com.example.food_delivery.service;

import com.example.food_delivery.dto.TokenResponse;
import com.example.food_delivery.dto.authuser.AuthUserCreateDto;
import com.example.food_delivery.dto.authuser.AuthUserUpdateDto;
import com.example.food_delivery.dto.authuser.AuthenticationRequest;

public interface AuthUserService {
    TokenResponse save(AuthUserCreateDto dto);
    TokenResponse login(AuthenticationRequest authenticationRequest);
    void update(AuthUserUpdateDto dto, Integer id);
}
