package com.project.shopping_cart.service;

import com.project.shopping_cart.dto.request.AddProductRequest;
import com.project.shopping_cart.dto.request.UpdateProductRequest;
import com.project.shopping_cart.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(AddProductRequest request);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(UpdateProductRequest request, Long id);

    List<Product> getAllProducts();

    List<Product> getAllProductsByCategory(String category);

    List<Product> getAllProductsByBrand(String brand);

    List<Product> getAllProductsByCategoryAndBrand(String category, String brand);

    List<Product> getAllProductsByName(String name);

    List<Product> getAllProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

}
