package com.project.shopping_cart.controller;

import com.project.shopping_cart.dto.ImageDto;
import com.project.shopping_cart.dto.response.ApiResponse;
import com.project.shopping_cart.model.Image;
import com.project.shopping_cart.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@CrossOrigin
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestBody List<MultipartFile> files,
                                                  @RequestParam Long productId) {
        List<ImageDto> savedImageDtos = imageService.saveImages(files, productId);
        return ResponseEntity.ok(new ApiResponse("Upload success", savedImageDtos));
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(
                image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(resource);
    }

    @PutMapping("/image/update/{imageId}")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file) {
        Image image = imageService.getImageById(imageId);
        imageService.updateImage(file, imageId);
        return ResponseEntity.ok(new ApiResponse("Update success", null));
    }

    @DeleteMapping("/image/delete/{imageId}")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId) {
        Image image = imageService.getImageById(imageId);
        imageService.deleteImageById(imageId);
        return ResponseEntity.ok(new ApiResponse("Delete success", null));
    }


}
