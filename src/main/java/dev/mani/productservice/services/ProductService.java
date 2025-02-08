package dev.mani.productservice.services;

import dev.mani.productservice.exceptions.ProductNotFoundException;
import dev.mani.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductByID(long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(String title,
                          String description,
                          Double price,
                          String Category,
                          String image);

    Product updateProduct(Long id, String title,
                          String description,
                          Double price,
                          String Category,
                          String image);

    Product deleteProduct(Long id);
}
