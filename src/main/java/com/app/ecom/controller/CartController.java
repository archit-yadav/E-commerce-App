package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public String addToCart(@RequestHeader("X-User-ID") String userId, @RequestBody CartItemRequest request){
        if (!cartService.addToCart(userId,request)){
            return "Product out of stock or User not found or product not found";
        }

        return "Product Added Successfully";

    }

}
