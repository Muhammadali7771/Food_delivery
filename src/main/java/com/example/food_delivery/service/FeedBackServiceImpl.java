package com.example.food_delivery.service;

import com.example.food_delivery.dto.FeedBackCreateDto;
import com.example.food_delivery.dto.FeedBackDto;
import com.example.food_delivery.entity.FeedBack;
import com.example.food_delivery.exception.ResourceNotFoundException;
import com.example.food_delivery.mapper.FeedBackMapper;
import com.example.food_delivery.repository.FeedBackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackRepository feedBackRepository;
    private final FeedBackMapper feedBackMapper;

    @Autowired
    public FeedBackServiceImpl(FeedBackRepository feedBackRepository, FeedBackMapper feedBackMapper) {
        this.feedBackRepository = feedBackRepository;
        this.feedBackMapper = feedBackMapper;
    }

    @Override
    public FeedBackDto addFeedback(FeedBackCreateDto feedbackCreateDto) {
        FeedBack feedback = feedBackMapper.toEntity(feedbackCreateDto);
        feedback.setSendAt(LocalDateTime.now());
        return feedBackMapper.toDto(feedBackRepository.save(feedback));
    }

    @Override
    public List<FeedBackDto> getAllFeedbacks() {
        return feedBackRepository.findAll()
                .stream()
                .map(feedBackMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FeedBackDto getFeedbackById(Integer id) {
        FeedBack feedback = feedBackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));
        return feedBackMapper.toDto(feedback);
    }

    @Override
    public void deleteFeedback(Integer id) {
        if (!feedBackRepository.existsById(id)) {
            throw new ResourceNotFoundException("Feedback not found with id: " + id);
        }
        feedBackRepository.deleteById(id);
    }
}