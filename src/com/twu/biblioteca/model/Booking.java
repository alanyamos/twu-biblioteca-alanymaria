package com.twu.biblioteca.model;

public class Booking {
    private Integer user;
    private Integer book;
    private Integer movie;

    public Booking(Integer user, Integer book, Integer movie) {
        this.user = user;
        this.book = book;
        this.movie = movie;
    }

    public Integer getBook() {
        return book;
    }

    public Integer getMovie() {
        return movie;
    }

    public Integer getUser() {
        return user;
    }
}
