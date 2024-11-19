package com.example.food_delivery.entity;

import com.example.food_delivery.dto.OrderItemDto;
import com.example.food_delivery.enums.ORDER_STATUS;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(List<OrderItemDto> items, LocalDateTime orderDateTime, double totalAmount, String address,
                       ORDER_STATUS status) {
}