package com.project.shopping_cart.repository;

import com.project.shopping_cart.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

}
