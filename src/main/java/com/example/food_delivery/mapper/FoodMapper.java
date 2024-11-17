package com.example.food_delivery.mapper;

import com.example.food_delivery.dto.food.FoodCreateDto;
import com.example.food_delivery.dto.food.FoodDto;
import com.example.food_delivery.dto.food.FoodUpdateDto;
import com.example.food_delivery.entity.Food;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FoodMapper {
    Food toEntity(FoodCreateDto foodCreateDto);

    FoodDto toDto(Food food);

    List<FoodDto> toDtoList(List<Food> foodList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Food partialUpdate(FoodUpdateDto foodUpdateDto, @MappingTarget Food food);
}