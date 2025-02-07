package dev.mani.productservice.services;

import dev.mani.productservice.dtos.CreateProductDto;
import dev.mani.productservice.dtos.FakeStoreProductDto;
import dev.mani.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductByID(long id) {
        String url = "https://fakestoreapi.com/products/" + id;
        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject(url, FakeStoreProductDto.class);

        return fakeStoreProduct.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        String url = "https://fakestoreapi.com/products";
        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject(url, FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProduct : fakeStoreProducts) {
            products.add(fakeStoreProduct.toProduct());
        }
        return products;
    }

    @Override
    public Product createProduct(String title, String description, Double price, String Category, String image) {
        String url = "https://fakestoreapi.com/products";
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(Category);
        fakeStoreProductDto.setImage(image);

        FakeStoreProductDto result = restTemplate.postForObject(url, fakeStoreProductDto, FakeStoreProductDto.class);
        return result.toProduct();
    }

}

