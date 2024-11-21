package com.example.food_delivery.controller;

import com.example.food_delivery.configuration.security.SessionUser;
import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.TokenResponse;
import com.example.food_delivery.dto.authuser.UserUserDto;
import com.example.food_delivery.dto.authuser.AuthUserCreateDto;
import com.example.food_delivery.dto.authuser.AuthUserUpdateDto;
import com.example.food_delivery.dto.authuser.AuthenticationRequest;
import com.example.food_delivery.service.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthUserController {
    private final AuthUserService authUserService;

    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<TokenResponse> register(@Valid @RequestBody AuthUserCreateDto dto) {
        TokenResponse token = authUserService.save(dto);
        return new BaseResponse<>(token);
    }

    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        TokenResponse token = authUserService.login(authenticationRequest);
        return new BaseResponse<>(token);
    }

    @GetMapping("/profile")
    public BaseResponse<UserUserDto> getUserProfile() {
        UserUserDto userProfile = authUserService.getUserProfile();
        return new BaseResponse<>(userProfile);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<Void> updateUserProfile(@RequestBody @Valid AuthUserUpdateDto dto) {
        authUserService.update(dto);
        return ResponseEntity.noContent().build();
    }
}




