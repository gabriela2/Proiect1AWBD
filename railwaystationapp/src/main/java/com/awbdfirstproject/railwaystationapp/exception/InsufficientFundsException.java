package com.awbdfirstproject.railwaystationapp.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Nu exista suficiente fonduri pentru a putea achizitiona acest bilet!");
    }
}
