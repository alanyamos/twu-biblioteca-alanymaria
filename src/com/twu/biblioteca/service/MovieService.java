package com.twu.biblioteca.service;

import com.twu.biblioteca.CheckoutMovieException;
import com.twu.biblioteca.Utils;
import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieService {
    private List<Movie> movies = new ArrayList<Movie>(
        Arrays.asList(new Movie(0, "Coraline", "2009", "Henry Selick", "9","not reserved"),
            new Movie(1, "Mad Max: Fury Road", "2015", "George Miller", "unrated", "not reserved"),
            new Movie(2, "Pan's Labyrinth", "2006", "Guillermo del Toro", "8", "not reserved"),
            new Movie(3, "Training Day", "2001", "Antoine Fuqua", "10", "not reserved"),
            new Movie(4, "Us", "2019", "Jordan Peele", "9", "not reserved")
        )
    );

    public List<Movie> listAll() {
        return this.movies;
    }

    public void checkoutMovie(String movieTitle, String status) throws CheckoutMovieException {
        try {
            Movie movie = movies.stream().filter(m -> Utils.removeAccents(m.getTitle()).equals(Utils.removeAccents(movieTitle))).findFirst().get();
            if (!Utils.isAvailable(movie, status)) {
                throw new CheckoutMovieException();
            }
            movie.setStatus(status);
        } catch (Exception error) {
            throw new CheckoutMovieException();
        }
    }

    public Movie getByTitle(String movieTitle) {
        return movies.stream().filter(m -> Utils.removeAccents(m.getTitle()).equals(Utils.removeAccents(movieTitle))).findFirst().get();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getMovieById(Integer movieId) {
        String movieTitle = "";
        try {
            Movie movie = movies.stream().filter(b -> b.getId() == movieId).findFirst().get();
            movieTitle = movie.getTitle();
        } catch (Exception error) {
        }

        return movieTitle;
    }
}
