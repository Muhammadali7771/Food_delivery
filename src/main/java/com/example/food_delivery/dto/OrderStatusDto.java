package com.example.food_delivery.dto;

import com.example.food_delivery.enums.ORDER_STATUS;

public record OrderStatusDto(
        Integer id,
        ORDER_STATUS status) {
}
