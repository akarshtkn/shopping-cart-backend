package com.project.shopping_cart.controller;

import com.project.shopping_cart.dto.ProductDto;
import com.project.shopping_cart.dto.request.AddProductRequest;
import com.project.shopping_cart.dto.request.UpdateProductRequest;
import com.project.shopping_cart.dto.response.ApiResponse;
import com.project.shopping_cart.model.Product;
import com.project.shopping_cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @PostMapping("/product/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        return ResponseEntity.ok()
                .body(new ApiResponse("Success", productService.addProduct(product)));
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long id, @RequestBody UpdateProductRequest product) {
        return ResponseEntity.ok()
                .body(new ApiResponse("Update success", productService.updateProduct(product, id)));
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().body(new ApiResponse("Delete Success", null));
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if(products.isEmpty()) {
            return ResponseEntity.ok().body(new ApiResponse("No products found", null));
        } else {
            List<ProductDto> productDtoList = productService.convertProductListToProductDtoList(products);
            return ResponseEntity.ok().body(new ApiResponse("List of products", productDtoList));
        }
    }

    @GetMapping("/product/get/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        return ResponseEntity.ok()
                .body(new ApiResponse("Product", productDto));
    }

    @GetMapping("/product/get/category")
    public ResponseEntity<ApiResponse> getAllProductsByCategory(@RequestParam String category) {
        List<Product> products = productService.getAllProductsByCategory(category);

        if(products.isEmpty()) {
            return ResponseEntity.ok().body(new ApiResponse("No products found", null));
        } else {
            List<ProductDto> productDtoList = productService.convertProductListToProductDtoList(products);
            return ResponseEntity.ok().body(new ApiResponse("List of products", productDtoList));
        }
    }

    @GetMapping("/product/get/brand")
    public ResponseEntity<ApiResponse> getAllProductsByBrand(@RequestParam String brand) {
        List<Product> products = productService.getAllProductsByBrand(brand);

        if(products.isEmpty()) {
            return ResponseEntity.ok().body(new ApiResponse("No products found", null));
        } else {
            List<ProductDto> productDtoList = productService.convertProductListToProductDtoList(products);
            return ResponseEntity.ok().body(new ApiResponse("List of products", productDtoList));
        }
    }

    @GetMapping("/product/get/category-and-brand")
    public ResponseEntity<ApiResponse> getAllProductsByCategoryAndBrand(
            @RequestParam String category, @RequestParam String brand) {
        List<Product> products = productService.getAllProductsByCategoryAndBrand(category, brand);

        if(products.isEmpty()) {
            return ResponseEntity.ok().body(new ApiResponse("No products found", null));
        } else {
            List<ProductDto> productDtoList = productService.convertProductListToProductDtoList(products);
            return ResponseEntity.ok().body(new ApiResponse("List of products", productDtoList));
        }
    }

    @GetMapping("/product/get/name")
    public ResponseEntity<ApiResponse> getAllProductsByName(@RequestParam String name) {
        List<Product> products = productService.getAllProductsByName(name);

        if(products.isEmpty()) {
            return ResponseEntity.ok().body(new ApiResponse("No products found", null));
        } else {
            List<ProductDto> productDtoList = productService.convertProductListToProductDtoList(products);
            return ResponseEntity.ok().body(new ApiResponse("List of products", productDtoList));
        }
    }

    @GetMapping("/product/get/brand-and-name")
    public ResponseEntity<ApiResponse> getAllProductsByBrandAndName(
            @RequestParam String brand, @RequestParam String name) {
        List<Product> products = productService.getAllProductsByBrandAndName(brand, name);

        if(products.isEmpty()) {
            return ResponseEntity.ok().body(new ApiResponse("No products found", null));
        } else {
            List<ProductDto> productDtoList = productService.convertProductListToProductDtoList(products);
            return ResponseEntity.ok().body(new ApiResponse("List of products", productDtoList));
        }
    }

    @GetMapping("/product/get-count/brand-and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(
            @RequestParam String brand, @RequestParam String name) {
        return ResponseEntity.ok().body(new ApiResponse("Count of products",
                        productService.countProductsByBrandAndName(brand, name)));
    }
}
