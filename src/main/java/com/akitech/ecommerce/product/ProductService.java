package com.akitech.ecommerce.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product addProduct(Product product) throws Exception{
        product = productRepository.save(product);
        return product;
    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }
}
