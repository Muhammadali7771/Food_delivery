package com.example.food_delivery.service;

import com.example.food_delivery.dto.OrderCreateDto;
import com.example.food_delivery.entity.OrderDto;

import java.util.List;

public interface OrderService {
    void placeOrder(OrderCreateDto dto);
    List<OrderDto> myOrders();
}
