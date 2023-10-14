package ru.liapkin.springbootsecondappurfu.exception;

public class ValidationFailedException extends Exception {

    public ValidationFailedException(String message) {
        super(message);
    }

}
