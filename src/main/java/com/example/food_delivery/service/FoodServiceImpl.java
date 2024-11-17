package com.example.food_delivery.service;

import com.example.food_delivery.dto.food.FoodCreateDto;
import com.example.food_delivery.dto.food.FoodDto;
import com.example.food_delivery.dto.food.FoodUpdateDto;
import com.example.food_delivery.entity.Category;
import com.example.food_delivery.entity.Food;
import com.example.food_delivery.exception.ResourceNotFoundException;
import com.example.food_delivery.mapper.FoodMapper;
import com.example.food_delivery.repository.CategoryRepository;
import com.example.food_delivery.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodMapper foodMapper;
    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;

    public FoodServiceImpl(FoodMapper foodMapper, FoodRepository foodRepository, CategoryRepository categoryRepository) {
        this.foodMapper = foodMapper;
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Integer save(FoodCreateDto dto, Integer categoryId) {
        Food food = foodMapper.toEntity(dto);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        food.setCategory(category);
        foodRepository.save(food);
        return food.getId();
    }

    @Override
    public void update(FoodUpdateDto dto, Integer id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found"));
        foodMapper.partialUpdate(dto, food);
        foodRepository.save(food);
    }

    @Override
    public void delete(Integer id) {
        if (!foodRepository.existsById(id)) {
            throw new ResourceNotFoundException("Food not found");
        }
        foodRepository.deleteById(id);
    }

    @Override
    public FoodDto getById(Integer id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found"));
        return foodMapper.toDto(food);
    }

    @Override
    public List<FoodDto> getAll() {
        List<Food> foodList = foodRepository.findAll();
        return foodMapper.toDtoList(foodList);
    }

    @Override
    public List<FoodDto> getAllByCategoryId(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        List<Food> foodList = category.getFoodList();
        return foodMapper.toDtoList(foodList);
    }
}
