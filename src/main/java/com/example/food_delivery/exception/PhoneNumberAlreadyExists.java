package com.example.food_delivery.exception;

public class PhoneNumberAlreadyExists extends RuntimeException{
    public PhoneNumberAlreadyExists(String message) {
        super(message);
    }
}
