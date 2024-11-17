package com.example.food_delivery.controller;

import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.CartDto;
import com.example.food_delivery.dto.CartItemRequestDto;
import com.example.food_delivery.dto.CartItemUpdateDto;
import com.example.food_delivery.service.CartService;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/myCart")
    public BaseResponse<CartDto> getMyCart() {
        CartDto myCart = cartService.getMyCart();
        return new BaseResponse<>(myCart);
    }

    @PostMapping("/add-to-cart/{foodId}")
    public ResponseEntity<Void> addFoodToCart(@PathVariable Integer foodId,
                                              @RequestBody CartItemRequestDto dto) {
        cartService.addProductToCart(foodId, dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-cart/{foodId}")
    public ResponseEntity<Void> updateCart(@PathVariable Integer foodId,
                                           @RequestBody CartItemUpdateDto dto) {
        cartService.updateQuantity(foodId, dto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/delete-product-to-cart/{foodId}")
    public ResponseEntity<Void> deleteFoodFromCart(@PathVariable Integer foodId){
        cartService.removeProductFromCart(foodId);
        return ResponseEntity.noContent().build();
    }
}
