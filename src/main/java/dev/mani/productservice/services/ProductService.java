package dev.mani.productservice.services;

import dev.mani.productservice.dtos.CreateProductDto;
import dev.mani.productservice.models.Product;
import java.util.List;

public interface ProductService {
    Product getProductByID(long id);
    List<Product> getAllProducts();
    Product createProduct(String title,
                          String description,
                          Double price,
                          String Category,
                          String image);
}
