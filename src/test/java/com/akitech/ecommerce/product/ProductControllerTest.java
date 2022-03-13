package com.akitech.ecommerce.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldAddProductSuccessfully() throws Exception {
        Product product = new Product(1, "Shirt", 3, "Men Shirt Slim fit");

        mockMvc.perform(post("/products/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFailWhenServiceReturnFailed() throws Exception {
        Product product = new Product(1, "Shirt", 3, "Men Shirt Slim fit");
        Mockito.when(productService.addProduct(Mockito.any())).thenThrow(new Exception());

        mockMvc.perform(post("/products/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(product.toString()))
                .andExpect(status().isInternalServerError());
    }

}