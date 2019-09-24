package com.twu.biblioteca;

public class Book {
    private String title;
    private String yearPublished;
    private String author;

    public Book(String title, String yearPublished, String author) {
        this.title = title;
        this.yearPublished = yearPublished;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public String getAuthor() {
        return author;
    }
}

