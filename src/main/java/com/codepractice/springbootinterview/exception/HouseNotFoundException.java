package com.codepractice.springbootinterview.exception;

public class HouseNotFoundException extends Exception{
    public HouseNotFoundException(String message) {
        super(message);
    }
}
