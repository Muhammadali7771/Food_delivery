package com.example.food_delivery.mapper;

import com.example.food_delivery.dto.category.CategoryCreateDto;
import com.example.food_delivery.dto.category.CategoryDto;
import com.example.food_delivery.dto.category.CategoryUpdateDto;
import com.example.food_delivery.entity.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    Category toEntity(CategoryCreateDto categoryCreateDto);

    CategoryDto toDto(Category category);
    List<CategoryDto> toDtoList(List<Category> categoryList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryUpdateDto categoryUpdateDto, @MappingTarget Category category);
}