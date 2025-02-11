package dev.mani.productservice.services;

import dev.mani.productservice.exceptions.ProductNotFoundException;
import dev.mani.productservice.models.Category;
import dev.mani.productservice.models.Product;
import dev.mani.productservice.repositories.CategoryRepository;
import dev.mani.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("databaseProductService")
public class DatabaseProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DatabaseProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getProductByID(long id) throws ProductNotFoundException {

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String title, String description, Double price, String categoryTitle, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        Optional<Category> categoryOptional = categoryRepository.findByTitle(categoryTitle);
        Category category = null;
        Date today = new Date();
        if (categoryOptional.isEmpty()) {
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            newCategory.setCreatedAt(today);
            newCategory.setUpdatedAt(today);

            category = categoryRepository.save(newCategory);
        } else {
            category = categoryOptional.get();
        }
        
        product.setCategory(category);
        product.setCreatedAt(today);
        product.setUpdatedAt(today);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, String title, String description, Double price, String Category, String image) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
