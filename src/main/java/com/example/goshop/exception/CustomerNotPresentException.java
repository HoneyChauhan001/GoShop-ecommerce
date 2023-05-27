package com.example.goshop.exception;

public class CustomerNotPresentException extends Exception {
    public CustomerNotPresentException(String customerNotPresent) {
        super(customerNotPresent);
    }
}
