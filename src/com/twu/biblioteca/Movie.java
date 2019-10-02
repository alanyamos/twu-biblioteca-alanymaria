package com.twu.biblioteca;

public class Movie implements IsAvailable {
    private String title;
    private String year;
    private String director;
    private String rating;
    private String status;

    public Movie(String title, String year, String director, String rating, String status) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.status = status;
    }

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
