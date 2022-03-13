package com.akitech.ecommerce.product;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @Test
    public void shouldAddProductSuccessfully() throws Exception {
        Product product = new Product();
        Product expectedRes = new Product(1, "Shirt", 3, "Men Shirt slim fit");
        when(productRepository.save(any())).thenReturn(expectedRes);

        Product actualRes = productService.addProduct(product);

        assertEquals(actualRes.getId(), expectedRes.getId());
    }

    @Test
    public void shouldReturnProductList() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Shirt", 3, "Men Shirt Slim fit"));
        products.add(new Product(2, "Pant", 3, "Men Pant Slim fit"));
        products.add(new Product(3, "TShirt", 3, "Men TShirt Slim fit"));
        int noOfProducts = products.size();
        Mockito.when(productRepository.findAll()).thenReturn(products);

        List<Product> actualResponse = productService.getProducts();

        assertEquals(actualResponse.size(), noOfProducts);
    }

}