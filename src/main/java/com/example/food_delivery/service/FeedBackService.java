package com.example.food_delivery.service;

import com.example.food_delivery.dto.FeedBackCreateDto;
import com.example.food_delivery.dto.FeedBackDto;

import java.util.List;

public interface FeedBackService {
    FeedBackDto addFeedback(FeedBackCreateDto feedbackCreateDto);
    List<FeedBackDto> getAllFeedbacks();
    FeedBackDto getFeedbackById(Integer id);
    void deleteFeedback(Integer id);
}
