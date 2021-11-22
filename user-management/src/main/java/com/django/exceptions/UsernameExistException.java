package com.django.exceptions;

public class UsernameExistException extends Exception {
    public UsernameExistException(String message) {
        super(message);
    }
}