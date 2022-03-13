package com.akitech.ecommerce.product;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

}