package com.awbdfirstproject.railwaystationapp.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String name) {
        super("Resursa " + name + " nu poate fi gasita!");
    }
}
