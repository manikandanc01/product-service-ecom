package dev.mani.productservice.dtos;

import dev.mani.productservice.models.Category;
import dev.mani.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;
    private Rating rating;

    @Getter
    @Setter
    public static class Rating {
        private double rate;
        private int count;
    }

    public Product toProduct() {
        Product product = new Product();

        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);

        Category category1 = new Category();
        category1.setTitle(category);
        product.setCategory(category1);

        product.setPrice(price);
        product.setImageUrl(image);

        return product;
    }

}

