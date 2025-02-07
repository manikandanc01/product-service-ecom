package dev.mani.productservice.controllers;

import dev.mani.productservice.dtos.CreateProductDto;
import dev.mani.productservice.models.Product;
import dev.mani.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        List<Product> resp = productService.getAllProducts();
        return resp;
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        Product resp =  productService.getProductByID(id);
        return resp;
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody CreateProductDto createProductDto) {
        return productService.createProduct(createProductDto.getTitle(),
                createProductDto.getDescription(),
                createProductDto.getPrice(),
                createProductDto.getCategory(),
                createProductDto.getImage());
    }
}
