package com.demo.productservicepractice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
 //   private int id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String category;
}
