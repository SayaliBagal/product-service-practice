package com.demo.productservicepractice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private String image;
    @ManyToOne
    private Category category;
}


//spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/product_service
//spring.datasource.username=product_service
//#spring.datasource.password=ThePassword
//spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
//spring.jpa.show-sql: true
//spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
