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
        books.add(new Book("Don Quixote", "1605", "Miguel de Cervantes"));
        books.add(new Book("Moby Dick", "1851", "Herman Melville"));
        books.add(new Book("Madame Bovary", "1856", "Gustave Flaubert"));
        books.add(new Book("Alice's Adventures in Wonderland", "1862", "Lewis Carroll"));
        books.add(new Book("Crime and Punishment", "1866", "Fyodor Dostoyevsky"));
        return books;
    }
}
