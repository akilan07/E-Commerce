package com.akitech.ecommerce.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document("Product")
public class Product {
    @Id
    private int id;
    private String name;
    private int quantity;
    private String description;
}
