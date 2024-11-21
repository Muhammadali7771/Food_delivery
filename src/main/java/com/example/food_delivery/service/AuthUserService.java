package com.example.food_delivery.service;

import com.example.food_delivery.dto.TokenResponse;
import com.example.food_delivery.dto.authuser.UserUserDto;
import com.example.food_delivery.dto.authuser.AuthUserCreateDto;
import com.example.food_delivery.dto.authuser.AuthUserUpdateDto;
import com.example.food_delivery.dto.authuser.AuthenticationRequest;
import com.example.food_delivery.entity.AuthUser;

public interface AuthUserService {
    TokenResponse save(AuthUserCreateDto dto);
    TokenResponse login(AuthenticationRequest authenticationRequest);

    AuthUser getById(Integer id);

    void update(AuthUserUpdateDto dto);


    UserUserDto getUserProfile();
}
