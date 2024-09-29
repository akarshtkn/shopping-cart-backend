package com.project.shopping_cart.service;

import com.project.shopping_cart.model.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryByNameIfExistOrElseCreateNew(String name);

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category addCategory(Category category);

    Category updateCategory(Category category, Long id);

    void deleteCategoryById(Long id);
}
