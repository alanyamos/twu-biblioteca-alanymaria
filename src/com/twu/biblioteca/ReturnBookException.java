package com.twu.biblioteca;

public class ReturnBookException extends Exception {
    private String message = "That is not a valid book to return.\n";

    @Override
    public String getMessage() {
        return message;
    }
}
