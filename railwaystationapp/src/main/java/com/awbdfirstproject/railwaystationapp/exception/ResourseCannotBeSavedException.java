package com.awbdfirstproject.railwaystationapp.exception;

public class ResourseCannotBeSavedException extends RuntimeException {
    public ResourseCannotBeSavedException(String name) {
        super("Resursa " + name + " nu poate fi salvata!");
    }
}
