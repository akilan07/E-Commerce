package com.akitech.ecommerce.common;

public class InternalException extends Exception {
    public InternalException() {
        super("Internal Error");
    }

    public InternalException(String message) {
        super(message);
    }
}
