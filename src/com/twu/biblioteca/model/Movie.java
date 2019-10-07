package com.twu.biblioteca.model;

import com.twu.biblioteca.IsAvailable;

public class Movie implements IsAvailable {
    private Integer id;
    private String title;
    private String year;
    private String director;
    private String rating;
    private String status;

    public Movie(Integer id, String title, String year, String director, String rating, String status) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.status = status;
    }

    public Integer getId() { return  this.id; }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
