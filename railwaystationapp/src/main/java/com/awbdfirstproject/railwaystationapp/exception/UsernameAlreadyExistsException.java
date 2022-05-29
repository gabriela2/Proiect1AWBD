package com.awbdfirstproject.railwaystationapp.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super("In baza de date exista deja inregistrat numele de utilizator " + message + "!");
    }
}
