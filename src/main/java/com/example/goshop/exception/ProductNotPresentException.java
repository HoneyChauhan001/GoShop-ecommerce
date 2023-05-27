package com.example.goshop.exception;

public class ProductNotPresentException extends Exception {
    public ProductNotPresentException(String thisProductIsNotPresent) {
        super(thisProductIsNotPresent);
    }
}
