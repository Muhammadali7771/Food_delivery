package com.example.food_delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedBackDto {
    private Integer id;
    private Integer userId;
    private Integer orderId;
    private String message;
    private String sendAt;
}
