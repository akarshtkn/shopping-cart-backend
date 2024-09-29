package com.project.shopping_cart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    private String downloadUrl;

    @Lob
    private Blob image;

    @ManyToOne
    @JoinColumn(name = "fk_product_id", referencedColumnName = "id")
    private Product product;
}
