package com.example.food_delivery.mapper;

import com.example.food_delivery.dto.CartItemDto;
import com.example.food_delivery.entity.CartItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemMapper {

    CartItemDto toDto(CartItem cartItem);
    List<CartItemDto> toDtoList(List<CartItem> items);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CartItem partialUpdate(CartItemDto cartItemDto, @MappingTarget CartItem cartItem);
}