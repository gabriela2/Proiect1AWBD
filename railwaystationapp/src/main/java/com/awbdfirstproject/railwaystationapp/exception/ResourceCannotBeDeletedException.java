package com.awbdfirstproject.railwaystationapp.exception;

public class ResourceCannotBeDeletedException extends RuntimeException {
    public ResourceCannotBeDeletedException(String name) {
        super("Resursa " + name + " nu poate fi stearsa!");
    }
}
