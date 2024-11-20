package com.example.food_delivery.entity;

import com.example.food_delivery.dto.OrderItemDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {

    OrderItemDto toDto(OrderItem orderItem);
    List<OrderItemDto> toDtoList(List<OrderItem> orderItems);
}