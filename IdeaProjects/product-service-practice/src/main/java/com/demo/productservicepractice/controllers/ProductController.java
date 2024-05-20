package com.demo.productservicepractice.controllers;

import com.demo.productservicepractice.dtos.ProductRequestDto;
import com.demo.productservicepractice.dtos.ProductResponseDto;
import com.demo.productservicepractice.dtos.ErrorDto;
import com.demo.productservicepractice.exceptions.ProductNotFoundException;
import com.demo.productservicepractice.models.Product;
import com.demo.productservicepractice.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;
    private ModelMapper modelMapper;
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductDetails(@PathVariable("id") int productId) throws ProductNotFoundException {
        Product product=productService.getSingleProduct(productId);
        return convertToProductResponseDto(product);
    }
    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products=productService.getAllProducts();
        List<ProductResponseDto> allProduct= new ArrayList<>();
        for(Product product:products){
            allProduct.add(convertToProductResponseDto(product));
        }
        return allProduct;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createNewProduct(@RequestBody ProductRequestDto productRequestDto) {
       Product product= productService.addProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
       ProductResponseDto productResponseDto=convertToProductResponseDto(product);
       return new ResponseEntity<>(productResponseDto,HttpStatus.CREATED);
    }

    private ProductResponseDto convertToProductResponseDto(Product product) {
        String categoryTitle = product.getCategory().getTitle();
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
        productResponseDto.setCategory(categoryTitle);
        return productResponseDto;
    }

//    //adding exception handler
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException (ProductNotFoundException productNotFoundException){
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage(productNotFoundException.getMessage());
//        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//    }
}
