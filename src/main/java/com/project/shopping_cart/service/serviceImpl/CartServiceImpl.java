package com.project.shopping_cart.service.serviceImpl;

import com.project.shopping_cart.exception.ResourceNotFoundException;
import com.project.shopping_cart.model.Cart;
import com.project.shopping_cart.model.CartItem;
import com.project.shopping_cart.repository.CartRepo;
import com.project.shopping_cart.service.CartItemService;
import com.project.shopping_cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo repository;
//    private final CartItemService cartItemService;
    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    @Override
    public Cart getCart(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Cart not found"));
    }

    @Override
    public void clearCart(Long id) {
//        Cart cart = getCart(id);
//        cartItemService.deleteItemsByCartId(id);
//        cart.getItems().clear();
//        repository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }

    @Override
    public Long initializeNewCart() {
        Cart newCart = new Cart();
        Long newCartId = cartIdGenerator.incrementAndGet();
        newCart.setId(newCartId);
        return repository.save(newCart).getId();

    }
}
