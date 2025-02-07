package dev.mani.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
}
