package com.example.food_delivery.service;

import com.example.food_delivery.configuration.security.SessionUser;
import com.example.food_delivery.dto.OrderCreateDto;
import com.example.food_delivery.dto.OrderDto;
import com.example.food_delivery.entity.*;
import com.example.food_delivery.enums.ORDER_STATUS;
import com.example.food_delivery.mapper.OrderMapper;
import com.example.food_delivery.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private final SessionUser sessionUser;
    private final AuthUserService authUserService;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public OrderServiceImpl(SessionUser sessionUser, AuthUserService authUserService, OrderMapper orderMapper,
                            OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.sessionUser = sessionUser;
        this.authUserService = authUserService;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void placeOrder(OrderCreateDto orderCreateDto) {
        Integer currentUserId = sessionUser.getId();
        AuthUser authUser = authUserService.getById(currentUserId);
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
            orderItems.add(new OrderItem(cartItem.getFood(), cartItem.getQuantity(), cartItem.getItemPrice(), order));
        }
        cart.setTotalPrice(0);
        cartItemRepository.deleteAll(cartItems);
        orderItemRepository.saveAll(orderItems);

    }

    public List<OrderDto> myOrders() {
        Integer currentUserId = sessionUser.getId();
        List<Order> orders = orderRepository.findOrderAllByAuthUserId(currentUserId);
        return orderMapper.toDto(orders);
    }
}
