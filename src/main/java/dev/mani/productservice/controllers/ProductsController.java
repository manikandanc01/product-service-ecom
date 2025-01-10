package dev.mani.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @GetMapping("/{name}")
    public String testMethod(@PathVariable String name) {
        return "Hello"+name;
    }
}
