package com.project.shopping_cart.service;

import com.project.shopping_cart.model.Cart;
import com.project.shopping_cart.model.CartItem;

import java.math.BigDecimal;

public interface CartItemService {
    void deleteItemsByCartId(Long id);

    void addItemToCart(Long cartId, Long productId, int quantity);

    void removeItemFromCart(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
