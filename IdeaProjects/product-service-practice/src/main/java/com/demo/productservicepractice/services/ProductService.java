package com.demo.productservicepractice.services;

import com.demo.productservicepractice.dtos.ProductResponseDto;
import com.demo.productservicepractice.exceptions.ProductNotFoundException;
import com.demo.productservicepractice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(int productId) throws ProductNotFoundException;
    public List<Product > getAllProducts();
    public Product addProduct(String title,
                              String description,
                              String image,
                              String category,
                              double price);
}
