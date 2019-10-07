package com.twu.biblioteca;

public class CheckoutBookException extends Exception {
    private String message = "Sorry, that book is not available.\n";

    @Override
    public String getMessage() {
        return message;
    }
}
