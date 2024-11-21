package com.example.food_delivery.mapper;

import com.example.food_delivery.dto.FeedBackCreateDto;
import com.example.food_delivery.dto.FeedBackDto;
import com.example.food_delivery.entity.FeedBack;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedBackMapper {

    @Mapping(source = "authUser.id", target = "userId")
    @Mapping(source = "orderId.id", target = "orderId")
    FeedBackDto toDto(FeedBack feedback);

    @Mapping(source = "userId", target = "authUser.id")
    @Mapping(source = "orderId", target = "orderId.id")
    FeedBack toEntity(FeedBackDto feedbackDto);

    @Mapping(source = "userId", target = "authUser.id")
    @Mapping(source = "orderId", target = "orderId.id")
    FeedBack toEntity(FeedBackCreateDto feedbackCreateDto);
}
