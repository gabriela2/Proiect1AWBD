package com.awbdfirstproject.railwaystationapp.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super("In baza de date exista deja inregistrat emailul " + message + "!");
    }
}
