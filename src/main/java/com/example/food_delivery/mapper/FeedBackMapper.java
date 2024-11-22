package com.example.food_delivery.mapper;

import com.example.food_delivery.dto.FeedBackCreateDto;
import com.example.food_delivery.dto.FeedBackDto;
import com.example.food_delivery.entity.FeedBack;
import io.swagger.v3.oas.annotations.info.License;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedBackMapper {

    FeedBackDto toDto(FeedBack feedback);
    List<FeedBackDto> toDto(List<FeedBack> feedBacks);

    FeedBack toEntity(FeedBackCreateDto feedbackCreateDto);
}
