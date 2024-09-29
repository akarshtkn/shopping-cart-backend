package com.project.shopping_cart.service.serviceImpl;

import com.project.shopping_cart.dto.request.AddProductRequest;
import com.project.shopping_cart.dto.request.UpdateProductRequest;
import com.project.shopping_cart.exception.ResourceNotFoundException;
import com.project.shopping_cart.model.Category;
import com.project.shopping_cart.model.Product;
import com.project.shopping_cart.repository.ProductRepo;
import com.project.shopping_cart.service.CategoryService;
import com.project.shopping_cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final ProductRepo repository;

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = categoryService.findCategoryByNameIfExistOrElseCreateNew(request.getCategory().getName());
        return repository.save(addNewProduct(request, category));
    }

    private Product addNewProduct(AddProductRequest request, Category category) {
        return Product.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .price(request.getPrice())
                .inventory(request.getInventory())
                .description(request.getDescription())
                .category(category)
            .build();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> { throw new ResourceNotFoundException("Product not found"); }
                );
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long id) {
        return repository.findById(id)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(repository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryService.findCategoryByNameIfExistOrElseCreateNew(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return repository.findByCategoryName(category);
    }

    @Override
    public List<Product> getAllProductsByBrand(String brand) {
        return repository.findByBrand(brand);
    }

    @Override
    public List<Product> getAllProductsByCategoryAndBrand(String category, String brand) {
        return repository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getAllProductsByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Product> getAllProductsByBrandAndName(String brand, String name) {
        return repository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return repository.countByBrandAndName(brand, name);
    }
}
