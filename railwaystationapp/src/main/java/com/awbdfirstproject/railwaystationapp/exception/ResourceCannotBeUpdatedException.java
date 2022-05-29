package com.awbdfirstproject.railwaystationapp.exception;

public class ResourceCannotBeUpdatedException extends RuntimeException {
    public ResourceCannotBeUpdatedException(String name) {
        super("Resursa " + name + " nu poate fi actualizata!");
    }
}
