package com.demo.productservicepractice.services;

import com.demo.productservicepractice.dtos.FakeStoreDto;
import com.demo.productservicepractice.dtos.ProductResponseDto;
import com.demo.productservicepractice.exceptions.ProductNotFoundException;
import com.demo.productservicepractice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(int productId) throws ProductNotFoundException {
       FakeStoreDto fakeStoreDto = restTemplate.getForObject(
               "https://fakestoreapi.com/products/" + productId, FakeStoreDto.class
       );
       if (fakeStoreDto == null) {
           throw new ProductNotFoundException("product with id "+productId+" not found"
           +" try with id less than 21");
       }

       return fakeStoreDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreDto[] fakeStoreDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreDto[].class
        );
        List<Product> products = new ArrayList<>();
        for (FakeStoreDto fakeStoreDto : fakeStoreDtos) {
            products.add(fakeStoreDto.toProduct());
        }
        return products;
    }

    @Override
    public Product addProduct(String title,
                              String description,
                              String image,
                              String category,
                              double price) {
        FakeStoreDto fakeStoreDto = new FakeStoreDto();
        fakeStoreDto.setTitle(title);
        fakeStoreDto.setDescription(description);
        fakeStoreDto.setImage(image);
        fakeStoreDto.setCategory(category);
        fakeStoreDto.setPrice(price);

        FakeStoreDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products", fakeStoreDto, FakeStoreDto.class
        );
        return response.toProduct();
    }
}
