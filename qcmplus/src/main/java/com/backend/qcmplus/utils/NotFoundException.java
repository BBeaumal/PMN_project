package com.backend.qcmplus.utils;

public class NotFoundException extends Exception {
    public NotFoundException(String object ,Long id) {
        super(object+" : " + id + " Not found");
    }

    public NotFoundException(String object) {
        super(object);
    }
}