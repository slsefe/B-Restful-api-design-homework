package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public class InvalidStudentException extends RuntimeException {

    public InvalidStudentException() {
        super("The student id is invalid");
    }
}
