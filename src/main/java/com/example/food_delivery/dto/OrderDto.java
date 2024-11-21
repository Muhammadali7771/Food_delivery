package com.example.food_delivery.dto;

import com.example.food_delivery.dto.OrderItemDto;
import com.example.food_delivery.enums.ORDER_STATUS;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(Integer id, List<OrderItemDto> items, LocalDateTime orderDateTime, double totalAmount, String address,
                       ORDER_STATUS status) {
}