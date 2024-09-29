package com.project.shopping_cart.service;

import com.project.shopping_cart.dto.ImageDto;
import com.project.shopping_cart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
