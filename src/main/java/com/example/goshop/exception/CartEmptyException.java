package com.example.goshop.exception;

public class CartEmptyException extends Exception {
    public CartEmptyException(String cartIsEmpty) {
        super(cartIsEmpty);
    }
}
