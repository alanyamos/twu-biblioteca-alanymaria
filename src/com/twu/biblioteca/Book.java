package com.twu.biblioteca;

public class Book implements IsAvailable {
    private String title;
    private String yearPublished;
    private String author;
    private String status;

    public Book(String title, String yearPublished, String author, String status) {
        this.title = title;
        this.yearPublished = yearPublished;
        this.author = author;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

