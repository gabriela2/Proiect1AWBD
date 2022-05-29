package com.awbdfirstproject.railwaystationapp.exception;

public class BalanceUpdateException extends RuntimeException {
    public BalanceUpdateException() {
        super("Balanta contului nu poate fi mai mica decat 0!");
    }
}
