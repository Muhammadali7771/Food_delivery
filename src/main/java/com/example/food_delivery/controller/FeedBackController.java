package com.example.food_delivery.controller;

import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.FeedBackCreateDto;
import com.example.food_delivery.dto.FeedBackDto;
import com.example.food_delivery.service.FeedBackService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedBackController {

    private final FeedBackService feedBackService;

    public FeedBackController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addFeedback(@Valid @RequestBody FeedBackCreateDto feedbackCreateDto) {
        feedBackService.addFeedback(feedbackCreateDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<List<FeedBackDto>> getAllFeedbacks() {
        List<FeedBackDto> feedbacks = feedBackService.getAllFeedbacks();
        return new BaseResponse<>(feedbacks);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<FeedBackDto> getFeedbackById(@PathVariable Integer id) {
        FeedBackDto feedback = feedBackService.getFeedbackById(id);
        return new BaseResponse<>(feedback);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id) {
        feedBackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
