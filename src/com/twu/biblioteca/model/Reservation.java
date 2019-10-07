package com.twu.biblioteca.model;

public class Reservation {
    private Integer user;
    private Integer book;
    private Integer movie;

    public Reservation(Integer user, Integer book, Integer movie) {
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
