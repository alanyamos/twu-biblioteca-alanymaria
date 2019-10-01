package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        List<Book> books = booksSetUp();
        Biblioteca biblioteca = new Biblioteca(System.out, books, new BufferedReader(new InputStreamReader(System.in)));
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
}
