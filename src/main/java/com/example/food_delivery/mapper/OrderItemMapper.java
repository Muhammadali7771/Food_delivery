package com.example.food_delivery.mapper;

import com.example.food_delivery.dto.OrderItemDto;
import com.example.food_delivery.entity.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {

    OrderItemDto toDto(OrderItem orderItem);
    List<OrderItemDto> toDtoList(List<OrderItem> orderItems);
}