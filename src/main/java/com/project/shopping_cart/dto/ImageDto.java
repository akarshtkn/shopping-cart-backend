package com.project.shopping_cart.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {
    private Long id;
    private String fileName;
    private String downloadUrl;
}
