package com.example.food_delivery.mapper;

import com.example.food_delivery.dto.authuser.AuthUserCreateDto;
import com.example.food_delivery.dto.authuser.AuthUserDto;
import com.example.food_delivery.dto.authuser.AuthUserUpdateDto;
import com.example.food_delivery.entity.AuthUser;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthUserMapper {
    AuthUser toEntity(AuthUserCreateDto authUserCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthUser partialUpdate(AuthUserUpdateDto authUserUpdateDto, @MappingTarget AuthUser authUser);

    AuthUserDto toDto(AuthUser authUser);
}