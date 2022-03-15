package com.akitech.ecommerce.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document("Product")
public class Product extends ProductType{
    @Id
    private int id;
    private String name;
    private int quantity;
    private String description;
    private boolean isDeleted = true;

    public Product(int id, String name, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }
}