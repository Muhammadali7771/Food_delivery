package com.example.food_delivery.mapper;

import com.example.food_delivery.dto.OrderCreateDto;
import com.example.food_delivery.entity.Order;
import com.example.food_delivery.dto.OrderDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderCreateDto orderCreateDto);

    OrderDto toDto(Order order);

    List<OrderDto> toDto(List<Order> orders);
}