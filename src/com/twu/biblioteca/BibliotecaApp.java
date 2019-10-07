package com.twu.biblioteca;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(System.out, new BufferedReader(new InputStreamReader(System.in)),
                new BookService(), new MovieService(), new UserService());
        biblioteca.factory();
        biblioteca.welcomeMessage();
        boolean isLogged = biblioteca.login();

        String option = "";
        while (option != Constants.exitOption) {
            if (isLogged) {
                option = biblioteca.displayMenu();
                biblioteca.optionHandler(option);
            } else {
                isLogged = biblioteca.login();
            }
        }
    }
}
