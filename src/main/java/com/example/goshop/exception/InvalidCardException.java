package com.example.goshop.exception;

public class InvalidCardException extends Exception {
    public InvalidCardException(String invalidCard) {
        super(invalidCard);
    }
}
