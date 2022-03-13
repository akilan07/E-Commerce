package com.akitech.ecommerce.product;

import com.akitech.ecommerce.common.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product addProduct(Product product) throws InternalException {
        try {
            Product responseProduct = productRepository.save(product);
            return responseProduct;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException(e.getMessage());
        }
    }

    public List<Product> getProducts() throws InternalException {
        try {
            List<Product> products = productRepository.findByIsDeleted(true);
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }

    public Product updateProduct(Product product) throws InternalException, NoProductFound {
        try {
            if (productRepository.existsByIdAndIsDeleted(product.getId(), true)) {
                Product responseProduct = productRepository.save(product);
                return responseProduct;
            } else {
                throw new NoProductFound();
            }
        } catch (NoProductFound noProductFound) {
            throw new NoProductFound();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }

    }

    public Product deleteProduct(int productID) throws InternalException, NoProductFound {
        try {
            if (productRepository.existsByIdAndIsDeleted(productID, true)) {
                Optional<Product> product = productRepository.findByIdAndIsDeleted(productID, true);
                product.get().setDeleted(false);
                Product responseProduct = productRepository.save(product.get());
                return responseProduct;
            } else {
                throw new NoProductFound();
            }
        } catch (NoProductFound noProductFound) {
            throw new NoProductFound();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }
}
