package com.example.food_delivery.controller;

import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.CartDto;
import com.example.food_delivery.dto.CartItemRequestDto;
import com.example.food_delivery.dto.CartItemUpdateDto;
import com.example.food_delivery.service.CartServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/myCart")
    public BaseResponse<CartDto> getMyCart() {
        CartDto myCart = cartService.getMyCart();
        return new BaseResponse<>(myCart);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<Void> addFoodToCart(
                                             @Valid @RequestBody CartItemRequestDto dto) {
        cartService.addProductToCart(dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-cart")
    public ResponseEntity<Void> updateCart(
                                           @Valid @RequestBody CartItemUpdateDto dto) {
        cartService.updateQuantity(dto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/delete-from-cart/{foodId}")
    public ResponseEntity<Void> deleteFoodFromCart(@PathVariable Integer foodId){
        cartService.removeProductFromCart(foodId);
        return ResponseEntity.noContent().build();
    }
}
