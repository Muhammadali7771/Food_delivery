package com.example.food_delivery.service;

import com.example.food_delivery.configuration.security.SessionUser;
import com.example.food_delivery.dto.FeedBackCreateDto;
import com.example.food_delivery.dto.FeedBackDto;
import com.example.food_delivery.entity.AuthUser;
import com.example.food_delivery.entity.FeedBack;
import com.example.food_delivery.entity.Order;
import com.example.food_delivery.exception.ResourceNotFoundException;
import com.example.food_delivery.mapper.FeedBackMapper;
import com.example.food_delivery.repository.FeedBackRepository;

import com.example.food_delivery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedBackServiceImpl implements FeedBackService {
    private final SessionUser sessionUser;
    private final FeedBackRepository feedBackRepository;
    private final OrderRepository orderRepository;
    private final FeedBackMapper feedBackMapper;
    private final AuthUserService authUserService;

    @Autowired
    public FeedBackServiceImpl(SessionUser sessionUser, FeedBackRepository feedBackRepository, OrderRepository orderRepository, FeedBackMapper feedBackMapper, AuthUserService authUserService) {
        this.sessionUser = sessionUser;
        this.feedBackRepository = feedBackRepository;
        this.orderRepository = orderRepository;
        this.feedBackMapper = feedBackMapper;
        this.authUserService = authUserService;
    }

    @Override
    public void addFeedback(FeedBackCreateDto dto) {
        Integer currentUserId = sessionUser.getId();
        AuthUser authUser = authUserService.getById(currentUserId);
        FeedBack feedback = feedBackMapper.toEntity(dto);
        Integer orderId = dto.orderId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        feedback.setOrder(order);
        feedback.setAuthUser(authUser);
        feedback.setSendAt(LocalDateTime.now());
        feedBackRepository.save(feedback);
    }

    @Override
    public List<FeedBackDto> getAllFeedbacks() {
        List<FeedBack> feedBacks = feedBackRepository.findAll();
        return feedBackMapper.toDto(feedBacks);
    }
}