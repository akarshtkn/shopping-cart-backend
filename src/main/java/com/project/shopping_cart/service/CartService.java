package com.project.shopping_cart.service;

import com.project.shopping_cart.model.Cart;

import java.math.BigDecimal;

public interface CartService {

    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

}
