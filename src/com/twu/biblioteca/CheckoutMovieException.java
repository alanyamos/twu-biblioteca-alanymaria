package com.twu.biblioteca;

public class CheckoutMovieException extends Exception {
    private String message = "Sorry, that movie is not available.\n";

    @Override
    public String getMessage() {
        return message;
    }
}
