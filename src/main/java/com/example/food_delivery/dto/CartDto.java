package com.example.food_delivery.dto;

import java.util.List;

public record CartDto(List<CartItemDto> items,
                      double totalPrice) {
}
