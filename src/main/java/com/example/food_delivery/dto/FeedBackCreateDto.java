package com.example.food_delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedBackCreateDto {
    private Integer userId;
    private Integer orderId;
    private String message;
}
