package com.example.food_delivery.controller;

import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.OrderCreateDto;
import com.example.food_delivery.entity.OrderDto;
import com.example.food_delivery.service.OrderService;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/making-order")
    public ResponseEntity<Void> requestOrder(@Valid @RequestBody OrderCreateDto dto) {
        orderService.placeOrder(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/my-orders")
    public BaseResponse<List<OrderDto>> myOrders(){
        List<OrderDto> orders = orderService.myOrders();
        return new BaseResponse<>(orders);
    }
}
