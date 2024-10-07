package com.project.shopping_cart.configuration;

import com.project.shopping_cart.dto.ImageDto;
import com.project.shopping_cart.dto.ProductDto;
import com.project.shopping_cart.model.Image;
import com.project.shopping_cart.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        convertProductToProductDto(modelMapper);

        return new ModelMapper();
    }


    private void convertProductToProductDto(ModelMapper modelMapper) {
        modelMapper.typeMap(Product.class, ProductDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> {
                        List<Image> images = src.getImages() != null ? src.getImages() : new ArrayList<>();
                        return images.stream()
                                .map(this::convertImageToImageDto)
                                .collect(Collectors.toList());
                    }, ProductDto::setImages);
                });
    }

    private ImageDto convertImageToImageDto(Image image) {
        return modelMapper().map(image, ImageDto.class);
    }
}
