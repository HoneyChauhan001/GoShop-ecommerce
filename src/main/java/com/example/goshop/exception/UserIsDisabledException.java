package com.example.goshop.exception;

public class UserIsDisabledException extends Exception {
    public UserIsDisabledException(String user_is_disabled) {
        super(user_is_disabled);
    }
}
