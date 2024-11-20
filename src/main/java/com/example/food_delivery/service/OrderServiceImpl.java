package com.example.food_delivery.service;

import com.example.food_delivery.configuration.security.SessionUser;
import com.example.food_delivery.dto.OrderCreateDto;
import com.example.food_delivery.entity.*;
import com.example.food_delivery.enums.ORDER_STATUS;
import com.example.food_delivery.mapper.OrderMapper;
import com.example.food_delivery.repository.AuthUserRepository;
import com.example.food_delivery.repository.OrderItemRepository;
import com.example.food_delivery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService{
    private final SessionUser sessionUser;
    private final AuthUserRepository authUserRepository;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(SessionUser sessionUser, AuthUserRepository authUserRepository, OrderMapper orderMapper,
                            OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository) {
        this.sessionUser = sessionUser;
        this.authUserRepository = authUserRepository;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void placeOrder(OrderCreateDto orderCreateDto){
        Integer currentUserId = sessionUser.getId();
        Optional<AuthUser> optionalUser = authUserRepository.findById(currentUserId);
        if (optionalUser.isPresent()) {
            AuthUser authUser = optionalUser.get();

            Cart cart = authUser.getCart();

            Order order = orderMapper.toEntity(orderCreateDto);
            order.setAuthUser(authUser);
            order.setTotalAmount(cart.getTotalPrice());
            order.setOrderDateTime(LocalDateTime.now());
            order.setStatus(ORDER_STATUS.CONFIRMED);
            orderRepository.save(order);
            List<CartItem> cartItems = cart.getItems();
            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                  orderItems.add(new OrderItem(cartItem.getFood(), cartItem.getQuantity(),cartItem.getItemPrice(),order));
            }
            orderItemRepository.saveAll(orderItems);
        }
    }

    public List<OrderDto> myOrders(){
        Integer currentUserId = sessionUser.getId();
        Optional<AuthUser> optionalAuthUser = authUserRepository.findById(currentUserId);
        if (optionalAuthUser.isPresent()){
            List<Order> orders = orderRepository.findOrderAllByAuthUserId(currentUserId);
            return orderMapper.toDto(orders);
        }
        return Collections.emptyList();
    }
}
