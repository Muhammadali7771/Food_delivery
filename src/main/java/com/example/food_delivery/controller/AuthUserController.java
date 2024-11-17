package com.example.food_delivery.controller;

import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.TokenResponse;
import com.example.food_delivery.dto.authuser.AuthUserCreateDto;
import com.example.food_delivery.dto.authuser.AuthUserUpdateDto;
import com.example.food_delivery.dto.authuser.AuthenticationRequest;
import com.example.food_delivery.service.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<TokenResponse> register(@RequestBody AuthUserCreateDto dto){
        TokenResponse token = authUserService.save(dto);
        return new BaseResponse<>(token);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<TokenResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        TokenResponse token = authUserService.login(authenticationRequest);
        return new BaseResponse<>(token);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> update(@Valid @RequestBody AuthUserUpdateDto dto,
                                       @PathVariable Integer id){
        authUserService.update(dto, id);
        return new BaseResponse<>("User has been updated");
    }
}
