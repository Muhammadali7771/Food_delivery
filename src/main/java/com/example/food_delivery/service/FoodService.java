package com.example.food_delivery.service;

import com.example.food_delivery.dto.food.FoodCreateDto;
import com.example.food_delivery.dto.food.FoodDto;
import com.example.food_delivery.dto.food.FoodUpdateDto;

import java.util.List;

public interface FoodService {
    Integer save(FoodCreateDto dto, Integer categoryId);
    void update(FoodUpdateDto dto, Integer id);
    void delete(Integer id);
    FoodDto getById(Integer id);
    List<FoodDto> getAll();
    List<FoodDto> getAllByCategoryId(Integer categoryId);
}
