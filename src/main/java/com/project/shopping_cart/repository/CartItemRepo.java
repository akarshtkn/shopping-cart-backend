package com.project.shopping_cart.repository;

import com.project.shopping_cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
