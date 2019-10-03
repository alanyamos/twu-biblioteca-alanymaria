package com.twu.biblioteca;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(System.out, new BufferedReader(new InputStreamReader(System.in)), new BookService(), new MovieService());
        biblioteca.factory();
        biblioteca.welcomeMessage();
        String option = "";
        while (option != Constants.exitOption) {
            option = biblioteca.displayMenu();
            biblioteca.optionHandler(option);
        }
    }
}
