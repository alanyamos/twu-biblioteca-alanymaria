package com.twu.biblioteca.model;

import com.twu.biblioteca.IsAvailable;

public class Book implements IsAvailable {
    private Integer id;
    private String title;
    private String yearPublished;
    private String author;
    private String status;

    public Book(Integer id, String title, String yearPublished, String author, String status) {
        this.id = id;
        this.title = title;
        this.yearPublished = yearPublished;
        this.author = author;
        this.status = status;
    }

    public Integer getId() { return  this.id; }

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

