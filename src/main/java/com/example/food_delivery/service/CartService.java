package com.example.food_delivery.service;

import com.example.food_delivery.configuration.security.SessionUser;
import com.example.food_delivery.dto.CartDto;
import com.example.food_delivery.dto.CartItemRequestDto;
import com.example.food_delivery.entity.AuthUser;
import com.example.food_delivery.entity.Cart;
import com.example.food_delivery.entity.CartItem;
import com.example.food_delivery.entity.Food;
import com.example.food_delivery.exception.ResourceNotFoundException;
import com.example.food_delivery.mapper.CartMapper;
import com.example.food_delivery.repository.AuthUserRepository;
import com.example.food_delivery.repository.CartItemRepository;
import com.example.food_delivery.repository.CartRepository;
import com.example.food_delivery.repository.FoodRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final SessionUser sessionUser;
    private final CartRepository cartRepository;
    private final AuthUserRepository authUserRepository;
    private final CartMapper cartMapper;
    private final FoodRepository foodRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(SessionUser sessionUser, CartRepository cartRepository, AuthUserRepository authUserRepository, CartMapper cartMapper, FoodRepository foodRepository, CartItemRepository cartItemRepository) {
        this.sessionUser = sessionUser;
        this.cartRepository = cartRepository;
        this.authUserRepository = authUserRepository;
        this.cartMapper = cartMapper;
        this.foodRepository = foodRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public CartDto getMyCart(){
        Integer currentUserId = sessionUser.getId();
        AuthUser authUser = authUserRepository.findById(currentUserId)
                .orElseThrow();
        Cart cart = authUser.getCart();
        return cartMapper.toDto(cart);
    }

    public void addProductToCart(Integer foodId, CartItemRequestDto dto){
        Integer currentUserId = sessionUser.getId();
        AuthUser authUser = authUserRepository.findById(currentUserId)
                .orElseThrow(() -> new AccessDeniedException("You are not registered!"));
        Cart cart = authUser.getCart();
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found"));
        Integer quantity = dto.quantity();

        CartItem cartItem = new CartItem();
        cartItem.setFood(food);
        cartItem.setQuantity(quantity);
        cartItem.setItemPrice(food.getPrice() * quantity);
        cartItem.setCart(cart);

        cart.setTotalPrice(cart.getTotalPrice() + cartItem.getItemPrice());
        cartItemRepository.save(cartItem);
    }
}
