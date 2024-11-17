package com.example.food_delivery.service;

import com.example.food_delivery.dto.category.CategoryCreateDto;
import com.example.food_delivery.dto.category.CategoryDto;
import com.example.food_delivery.dto.category.CategoryUpdateDto;
import com.example.food_delivery.entity.Category;

import java.util.List;


public interface CategoryService {

    Integer save(CategoryCreateDto categoryCreateDto);
    void delete(Integer id);
    void update(CategoryUpdateDto dto, Integer id);
    CategoryDto getById(Integer id);
    List<CategoryDto> getAll();


}
