package com.akitech.ecommerce.product;

import com.akitech.ecommerce.common.InternalException;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> addProduct(@RequestBody Product product) throws Exception {
        Product response = productService.addProduct(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Product>> getProducts() throws InternalException {
        List<Product> products = productService.getProducts();
        var status = products.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(products, status);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception{
        Product response = productService.updateProduct(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productID}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Product> deleteProduct(@PathVariable int productID) throws Exception{
        Product response = productService.deleteProduct(productID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
