package com.django.exceptions;

public class NotAnImageFileException extends Exception {
    public NotAnImageFileException(String message) {
        super(message);
    }
}
