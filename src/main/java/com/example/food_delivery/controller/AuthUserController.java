package com.example.food_delivery.controller;

import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.EmailPasswordResetDto;
import com.example.food_delivery.dto.ForgetPasswordTokenDto;
import com.example.food_delivery.dto.TokenResponse;
import com.example.food_delivery.dto.authuser.AuthUserCreateDto;
import com.example.food_delivery.dto.authuser.AuthUserDto;
import com.example.food_delivery.dto.authuser.AuthUserUpdateDto;
import com.example.food_delivery.dto.authuser.AuthenticationRequest;
import com.example.food_delivery.service.AuthUserService;
import com.example.food_delivery.service.ForgotResetTokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/auth")
public class AuthUserController {
    private final AuthUserService authUserService;
    private final ForgotResetTokenService forgotResetTokenService;

    public AuthUserController(AuthUserService authUserService, ForgotResetTokenService forgotResetTokenService) {
        this.authUserService = authUserService;
        this.forgotResetTokenService = forgotResetTokenService;
    }

    @PostMapping("/api/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<TokenResponse> register(@Valid @RequestBody AuthUserCreateDto dto) {
        TokenResponse token = authUserService.save(dto);
        return new BaseResponse<>(token);
    }


    @PostMapping("/api/auth/login")
    public BaseResponse<TokenResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        TokenResponse token = authUserService.login(authenticationRequest);
        return new BaseResponse<>(token);
    }

    @GetMapping("/api/auth/profile")
    public BaseResponse<AuthUserDto> getUserProfile() {
        AuthUserDto userProfile = authUserService.getUserProfile();
        return new BaseResponse<>(userProfile);
    }

    @PutMapping("/api/auth/profile/update")
    public ResponseEntity<Void> updateUserProfile(@RequestBody @Valid AuthUserUpdateDto dto) {
        authUserService.update(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/auth/forget-password")
    public ResponseEntity<Void> forgetPassword(@RequestBody EmailPasswordResetDto dto){
       forgotResetTokenService.sendTokenToEmail(dto.email());
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/auth/reset-password")
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody ForgetPasswordTokenDto dto){
        forgotResetTokenService.verify(dto);
        return ResponseEntity.noContent().build();
    }
}




