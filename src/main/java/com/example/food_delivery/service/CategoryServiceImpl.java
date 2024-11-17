package com.example.food_delivery.service;

import com.example.food_delivery.dto.category.CategoryCreateDto;
import com.example.food_delivery.dto.category.CategoryDto;
import com.example.food_delivery.dto.category.CategoryUpdateDto;
import com.example.food_delivery.entity.Category;
import com.example.food_delivery.exception.ResourceNotFoundException;
import com.example.food_delivery.mapper.CategoryMapper;
import com.example.food_delivery.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Integer save(CategoryCreateDto categoryCreateDto) {
        Category category = categoryMapper.toEntity(categoryCreateDto);
        Category savedCategory = categoryRepository.save(category);
        return savedCategory.getId();
    }

    @Override
    public void delete(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category with ID " + id + " not found.");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(CategoryUpdateDto categoryUpdateDto, Integer id) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found."));
        Category category = categoryMapper.partialUpdate(categoryUpdateDto, existingCategory);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDto getById(Integer id) {
       Category category = categoryRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found."));
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.toDtoList(categoryList);
    }
}