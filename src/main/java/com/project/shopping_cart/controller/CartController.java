package com.project.shopping_cart.controller;

import com.project.shopping_cart.dto.response.ApiResponse;
import com.project.shopping_cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/get/{cartId}")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(new ApiResponse("Success", cartService.getCart(cartId)));
    }

    @DeleteMapping("/cart/clear/{cartId}")
    public ResponseEntity<ApiResponse> clearCart( @PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok(new ApiResponse("Clear Cart Success!", null));
    }

    @GetMapping("/cart/total-price/{cartId}")
    public ResponseEntity<ApiResponse> getTotalAmount( @PathVariable Long cartId) {
        return ResponseEntity.ok(new ApiResponse("Total Price", cartService.getTotalPrice(cartId)));
    }
}
