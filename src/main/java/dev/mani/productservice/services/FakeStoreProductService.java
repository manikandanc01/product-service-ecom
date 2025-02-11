package dev.mani.productservice.services;

import dev.mani.productservice.dtos.FakeStoreProductDto;
import dev.mani.productservice.exceptions.ProductNotFoundException;
import dev.mani.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductByID(long id) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/" + id;
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(url, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        return fakeStoreProductDto.toProduct();
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

    @Override
    public Product updateProduct(Long id, String title, String description, Double price, String Category, String image) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/" + id;
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(Category);
        fakeStoreProductDto.setImage(image);

        /*
         * for put, patch, delete no Response entity,
         * usually these api's are used for modify the resource and mostly don't return any response to the user
         * so there is no putForEntity, patchforentity and so on
         * use exchange instead
         * */

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(fakeStoreProductDto),
                FakeStoreProductDto.class,
                id
        );

        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }

        if (response.getBody() == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        return response.getBody().toProduct();
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/" + id;
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id
        );

        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }

        if (response.getBody() == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        return response.getBody().toProduct();
    }

}

