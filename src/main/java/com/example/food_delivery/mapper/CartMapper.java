package com.example.food_delivery.mapper;

import com.example.food_delivery.dto.CartDto;
import com.example.food_delivery.entity.Cart;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartMapper {
    CartDto toDto(Cart cart);

}