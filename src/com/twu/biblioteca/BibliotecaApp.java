package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        List<Book> books = booksSetUp();
        List<Movie> movies = moviesSetUp();
        Biblioteca biblioteca = new Biblioteca(System.out, books, movies, new BufferedReader(new InputStreamReader(System.in)));
        biblioteca.factory();
        biblioteca.welcomeMessage();
        String option = "";
        while (option != Constants.exitOption) {
            option = biblioteca.displayMenu();
            biblioteca.optionHandler(option);
        }
    }

    private static List<Book> booksSetUp() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Don Quixote", "1605", "Miguel de Cervantes", "not reserved"));
        books.add(new Book("Moby Dick", "1851", "Herman Melville", "not reserved"));
        books.add(new Book("Madame Bovary", "1856", "Gustave Flaubert", "not reserved"));
        books.add(new Book("Alice's Adventures in Wonderland", "1862", "Lewis Carroll", "not reserved"));
        books.add(new Book("Crime and Punishment", "1866", "Fyodor Dostoyevsky", "not reserved"));
        return books;
    }

    private static List<Movie> moviesSetUp() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("Coraline", "2009", "Henry Selick", "9","not reserved"));
        movies.add(new Movie("Mad Max: Fury Road", "2015", "George Miller", "unrated", "not reserved"));
        movies.add(new Movie("Pan's Labyrinth", "2006", "Guillermo del Toro", "8", "not reserved"));
        movies.add(new Movie("Training Day", "2001", "Antoine Fuqua", "10", "not reserved"));
        movies.add(new Movie("Us", "2019", "Jordan Peele", "9", "not reserved"));
        return movies;
    }
}
