package com.project.shopping_cart.service.serviceImpl;

import com.project.shopping_cart.dto.ImageDto;
import com.project.shopping_cart.exception.ResourceNotFoundException;
import com.project.shopping_cart.model.Image;
import com.project.shopping_cart.model.Product;
import com.project.shopping_cart.repository.ImageRepo;
import com.project.shopping_cart.service.ImageService;
import com.project.shopping_cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ProductService productService;
    private final ImageRepo repository;

    @Override
    public Image getImageById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,() -> {
                    throw new ResourceNotFoundException("Image not found with id " + id);
                });
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files , Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImageDto = new ArrayList<>();
        for(MultipartFile file : files) {
            try {
                Image image = Image.builder()
                        .fileName(file.getOriginalFilename())
                        .fileType(file.getContentType())
                        .image(new SerialBlob(file.getBytes()))
                        .product(product)
                        .build();

                Image savedImage = repository.save(image);
                String downloadUrl = "/api/v1/images/image/download/" + savedImage.getId();
                image.setDownloadUrl(downloadUrl);
                repository.save(savedImage);

                ImageDto imageDto = ImageDto.builder()
                        .id(savedImage.getId())
                        .fileName(savedImage.getFileName())
                        .downloadUrl(savedImage.getDownloadUrl())
                        .build();
                savedImageDto.add(imageDto);

            } catch(SQLException | IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(image.getFileType());
            image.setImage(new SerialBlob(file.getBytes()));
            repository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
