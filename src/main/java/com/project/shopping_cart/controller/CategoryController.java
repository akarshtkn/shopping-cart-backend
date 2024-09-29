package com.project.shopping_cart.controller;

import com.project.shopping_cart.dto.response.ApiResponse;
import com.project.shopping_cart.model.Category;
import com.project.shopping_cart.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok().body(new ApiResponse("List of categories", categories));
    }

    @PostMapping("/category/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok()
                .body(new ApiResponse("Success", categoryService.addCategory(category)));
    }

    @GetMapping("/category/get/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(new ApiResponse("Category", categoryService.getCategoryById(id)));
    }

    @GetMapping("/category/get/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok()
                .body(new ApiResponse("Category", categoryService.getCategoryByName(name)));
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok()
                .body(new ApiResponse("Delete success", null));
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category){
        return ResponseEntity.ok()
                .body(new ApiResponse("Update Success", categoryService.updateCategory(category, id)));
    }
}
