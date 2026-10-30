package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.CartItem;
import com.app.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartItem> getAllCartItems(@RequestHeader("X-User-ID") String userId){
        return cartService.getAllCartItems(userId);
    }

    @PostMapping
    public String addToCart(@RequestHeader("X-User-ID") String userId, @RequestBody CartItemRequest request){
        if (!cartService.addToCart(userId,request)){
            return "Product out of stock or User not found or product not found";
        }

        return "Product Added Successfully";

    }

    @DeleteMapping("/{productId}")
    public String deleteItemFromCart(@RequestHeader("X-User-ID") String userId ,@PathVariable Long productId){

       boolean deleted = cartService.deleteItemFromCart(userId,productId);

        return deleted ? "Product Deleted Successfully" : "Product not found or user id is not found" ;

    }
}
