package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        List<Book> books = booksSetUp();
        Biblioteca biblioteca = new Biblioteca(System.out, books);
        biblioteca.printWelcomeMessage();
        biblioteca.listAllBooks();
    }

    private static List<Book> booksSetUp() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Moby Dick"));
        books.add(new Book("Don Quixote"));
        books.add(new Book("Madame Bovary"));
        books.add(new Book("Alice's Adventures in Wonderland"));
        books.add(new Book("Crime and Punishment"));
        return books;
    }
}
