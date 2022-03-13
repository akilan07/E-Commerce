package com.akitech.ecommerce.product;

public class NoProductFound extends Exception {
    public NoProductFound() {
        super("No product found");
    }

    public NoProductFound(String message) {
        super(message);
    }
}
