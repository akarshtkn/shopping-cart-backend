package com.project.shopping_cart.service.serviceImpl;

import com.project.shopping_cart.exception.ResourceAlreadyExistsException;
import com.project.shopping_cart.exception.ResourceNotFoundException;
import com.project.shopping_cart.model.Category;
import com.project.shopping_cart.repository.CategoryRepo;
import com.project.shopping_cart.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo repository;

    @Override
    public Category findCategoryByNameIfExistOrElseCreateNew(String name) {
        return Optional.ofNullable(repository.findByName(name))
                .orElseGet(() -> repository.save(new Category(name)));

    }

    @Override
    public Category getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(c -> !repository.existsByName(c.getName()))
                .map(repository::save)
                .orElseThrow(() -> new ResourceNotFoundException(category.getName() + " already exists"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory -> {
                    oldCategory.setName(category.getName());
                    return repository.save(oldCategory);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategoryById(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete, () -> {
                    throw new ResourceNotFoundException("Category not found");
                });
    }
}
