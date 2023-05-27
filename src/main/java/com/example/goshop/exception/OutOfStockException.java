package com.example.goshop.exception;

public class OutOfStockException extends Exception {
    public OutOfStockException(String productOutOfStock) {
        super(productOutOfStock);
    }
}
