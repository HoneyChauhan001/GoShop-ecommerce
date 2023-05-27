package com.example.goshop.exception;

public class InSufficientQuantityException extends Exception {
    public InSufficientQuantityException(String insufficientQuantityOfProduct) {
        super(insufficientQuantityOfProduct);
    }
}
