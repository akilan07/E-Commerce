package com.akitech.ecommerce.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

    List<Product> findByIsDeleted(boolean isDeleted);

    boolean existsByIdAndIsDeleted(int id, boolean isDeleted);

    Optional<Product> findByIdAndIsDeleted(int id, boolean isDeleted);

}
