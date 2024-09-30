package com.project.shopping_cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ProductDtoList {
    private List<ProductDto> productDtoList;
}
