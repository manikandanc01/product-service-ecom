package dev.mani.productservice.controllers;

import dev.mani.productservice.dtos.CreateProductDto;
import dev.mani.productservice.dtos.UpdateProductDto;
import dev.mani.productservice.exceptions.ProductNotFoundException;
import dev.mani.productservice.models.Product;
import dev.mani.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(@Qualifier("databaseProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> resp = productService.getAllProducts();
        ResponseEntity<List<Product>> response;
        if (resp.isEmpty()) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<>(resp, HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        Product product = productService.getProductByID(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody CreateProductDto createProductDto) {
        Product product = productService.createProduct(createProductDto.getTitle(),
                createProductDto.getDescription(),
                createProductDto.getPrice(),
                createProductDto.getCategory(),
                createProductDto.getImage());

        ResponseEntity<Product> response;
        if (product == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(product, HttpStatus.CREATED);
        }

        return response;
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductDto updateProductDto) {
        Product product = productService.updateProduct(
                id,
                updateProductDto.getTitle(),
                updateProductDto.getDescription(),
                updateProductDto.getPrice(),
                updateProductDto.getCategory(),
                updateProductDto.getImage()
        );

        ResponseEntity<Product> resp;
        if (product == null) {
            resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            resp = new ResponseEntity<>(product, HttpStatus.OK);
        }

        return resp;
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
        Product product = productService.deleteProduct(id);
        ResponseEntity<Product> resp;
        if (product == null) {
            resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            resp = new ResponseEntity<>(product, HttpStatus.OK);
        }

        return resp;
    }

}
