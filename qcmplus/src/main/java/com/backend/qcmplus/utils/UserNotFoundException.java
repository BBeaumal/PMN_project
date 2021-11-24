package com.backend.qcmplus.utils;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super("User : " + id + " Not found");
    }
}