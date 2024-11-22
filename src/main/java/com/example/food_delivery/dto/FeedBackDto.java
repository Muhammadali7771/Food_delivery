package com.example.food_delivery.dto;

import com.example.food_delivery.dto.authuser.AuthUserDto;
import com.example.food_delivery.entity.AuthUser;
import com.example.food_delivery.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FeedBackDto {
    private Integer id;
    private AuthUserDto authUser;
    private String message;
    private LocalDateTime sendAt;
}
