package com.example.goshop.exception;

public class SellerDoesNotExistException extends Exception {
    public SellerDoesNotExistException(String sellerDoesNotExist) {
        super(sellerDoesNotExist);
    }
}
