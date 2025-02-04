package com.demo.productservicepractice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
