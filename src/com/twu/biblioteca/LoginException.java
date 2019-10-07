package com.twu.biblioteca;

public class LoginException extends Exception {
    private String message = "Invalid number or password. Please, try again!\n";

    @Override
    public String getMessage() {
        return message;
    }
}
