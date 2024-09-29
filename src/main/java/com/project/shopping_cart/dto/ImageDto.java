package com.project.shopping_cart.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {
    private Long imageId;
    private String imageName;
    private String downloadUrl;
}
