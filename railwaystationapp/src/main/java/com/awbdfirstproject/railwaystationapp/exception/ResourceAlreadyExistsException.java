package com.awbdfirstproject.railwaystationapp.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super("In baza de date exista deja resursa " + message + "!");
    }
}
