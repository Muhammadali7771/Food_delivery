package com.example.food_delivery.dto;

import lombok.Getter;
import lombok.Setter;

public record FeedBackCreateDto(Integer orderId, String message) {
}
