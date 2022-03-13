package com.akitech.ecommerce.product;

import com.akitech.ecommerce.common.InternalException;
import com.mongodb.MongoException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
        Mockito.when(productRepository.findByIsDeleted(anyBoolean())).thenReturn(products);

        List<Product> actualResponse = productService.getProducts();

        assertEquals(actualResponse.size(), noOfProducts);
    }

    @Test
    public void shouldUpdateProductSuccessfully() throws Exception {
        Product product = new Product();
        Product expectedRes = new Product(1, "Shirt", 3, "Men Shirt slim fit");
        when(productRepository.existsByIdAndIsDeleted(anyInt(), anyBoolean())).thenReturn(true);
        when(productRepository.save(any())).thenReturn(expectedRes);

        Product actualRes = productService.updateProduct(product);

        assertEquals(actualRes.getId(), expectedRes.getId());
    }

    @Test
    public void shouldReturnNoProductFound() throws Exception {
        Product product = new Product();
        when(productRepository.existsByIdAndIsDeleted(anyInt(), anyBoolean())).thenReturn(false);

        assertThrows(NoProductFound.class, () -> productService.updateProduct(product));
    }

    @Test
    public void shouldReturnInternalErrorIfUpdateFailed() throws Exception {
        Product product = new Product();
        when(productRepository.existsByIdAndIsDeleted(anyInt(), anyBoolean())).thenReturn(true);
        when(productRepository.save(any())).thenThrow(new MongoException("Update Failed"));

        assertThrows(InternalException.class, () -> productService.updateProduct(product));
    }

    @Test
    public void shouldSoftDeleteProductSuccessfully() throws Exception {
        int productID = 1;
        Product expectedRes = new Product(1, "Shirt", 3, "Men Shirt slim fit");
        when(productRepository.existsByIdAndIsDeleted(anyInt(), anyBoolean())).thenReturn(true);
        when(productRepository.findByIdAndIsDeleted(anyInt(), anyBoolean())).thenReturn(Optional.of(expectedRes));
        expectedRes.setDeleted(false);
        when(productRepository.save(any())).thenReturn(expectedRes);

        Product actualProduct = productService.deleteProduct(productID);

        assertFalse(actualProduct.isDeleted());
    }

}