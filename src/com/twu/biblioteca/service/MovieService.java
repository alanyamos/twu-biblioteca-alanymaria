package com.twu.biblioteca.service;

import com.twu.biblioteca.UpdateException;
import com.twu.biblioteca.Utils;
import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieService {
    private List<Movie> movies = new ArrayList<Movie>(
            Arrays.asList(new Movie("Coraline", "2009", "Henry Selick", "9","not reserved"),
                new Movie("Mad Max: Fury Road", "2015", "George Miller", "unrated", "not reserved"),
                new Movie("Pan's Labyrinth", "2006", "Guillermo del Toro", "8", "not reserved"),
                new Movie("Training Day", "2001", "Antoine Fuqua", "10", "not reserved"),
                new Movie("Us", "2019", "Jordan Peele", "9", "not reserved")
            )
    );

    public List<Movie> listAll() {
        return this.movies;
    }

    public void update(String bookTitle, String status) throws UpdateException {
        boolean failedUpdate = true;

        for (Movie movie : movies) {
            if (Utils.removeAccents(movie.getTitle()).equals(Utils.removeAccents(bookTitle)) && Utils.isAvailable(movie, status)) {
                movie.setStatus(status);
                failedUpdate = false;

            }
        }

        if (failedUpdate) {
            throw new UpdateException();
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
