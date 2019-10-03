package com.twu.biblioteca.service;

import com.twu.biblioteca.UpdateException;
import com.twu.biblioteca.Utils;
import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookService {

    private List<Book> books = new ArrayList<Book>(
        Arrays.asList(new Book("Don Quixote", "1605", "Miguel de Cervantes", "not reserved"),
            new Book("Moby Dick", "1851", "Herman Melville", "not reserved"),
            new Book("Madame Bovary", "1856", "Gustave Flaubert", "not reserved"),
            new Book("Alice's Adventures in Wonderland", "1862", "Lewis Carroll", "not reserved"),
            new Book("Crime and Punishment", "1866", "Fyodor Dostoyevsky", "not reserved")
        )
    );

    public List<Book> listAll() {
        return this.books;
    }

    public void update(String bookTitle, String status) throws UpdateException {
        boolean failedUpdate = true;

        for (Book book : books) {
            if (Utils.removeAccents(book.getTitle()).equals(Utils.removeAccents(bookTitle)) && Utils.isAvailable(book, status)) {
                book.setStatus(status);
                failedUpdate = false;

            }
        }

        if (failedUpdate) {
            throw new UpdateException();
        }
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
