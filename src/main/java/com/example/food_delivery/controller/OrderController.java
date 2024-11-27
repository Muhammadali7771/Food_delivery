package com.example.food_delivery.controller;

import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.OrderCreateDto;
import com.example.food_delivery.dto.OrderDto;
import com.example.food_delivery.dto.OrderStatusDto;
import com.example.food_delivery.service.OrderService;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/my-orders")
    public BaseResponse<List<OrderDto>> myOrders(){
        List<OrderDto> orders = orderService.myOrders();
        return new BaseResponse<>(orders);
    }
    @GetMapping("/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<List<OrderDto>> getAllOrders(){
        List<OrderDto> orders = orderService.getAllOrders();
        return new BaseResponse<>(orders);
    }


    @PutMapping("/change-order-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> changeOrderStatus(@RequestBody OrderStatusDto dto){
         orderService.changeOrderStatus(dto);
         return ResponseEntity.noContent().build();
    }
}
