package com.example.exception;

public class UnathorizedExeption extends RuntimeException{
    public UnathorizedExeption(String message){
        super(message);
    }
}
