package com.twu.biblioteca.service;

import com.twu.biblioteca.CheckoutBookException;
import com.twu.biblioteca.ReturnBookException;
import com.twu.biblioteca.Utils;
import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookService {

    private List<Book> books = new ArrayList<Book>(
        Arrays.asList(new Book(0, "Don Quixote", "1605", "Miguel de Cervantes", "not reserved"),
            new Book(1, "Moby Dick", "1851", "Herman Melville", "not reserved"),
            new Book(2, "Madame Bovary", "1856", "Gustave Flaubert", "not reserved"),
            new Book(3, "Alice's Adventures in Wonderland", "1862", "Lewis Carroll", "not reserved"),
            new Book(4, "Crime and Punishment", "1866", "Fyodor Dostoyevsky", "not reserved")
        )
    );

    public List<Book> listAll() {
        return this.books;
    }

    public void checkoutBook(String bookTitle, String status) throws CheckoutBookException {
        try {
            Book book = books.stream().filter(b -> Utils.removeAccents(b.getTitle()).equals(Utils.removeAccents(bookTitle))).findFirst().get();
            if (!Utils.isAvailable(book, status)) {
                throw new CheckoutBookException();
            }
            book.setStatus(status);
        } catch (Exception error) {
            throw new CheckoutBookException();
        }
    }

    public void returnBook(String bookTitle, String status) throws ReturnBookException {
        try {
            Book book = books.stream().filter(b -> Utils.removeAccents(b.getTitle()).equals(Utils.removeAccents(bookTitle))).findFirst().get();
            if (!Utils.isAvailable(book, status)) {
                throw new ReturnBookException();
            }
            book.setStatus(status);
        } catch (Exception error) {
            throw new ReturnBookException();
        }
    }

    public Book getByTitle(String bookTitle) {
        return books.stream().filter(b -> Utils.removeAccents(b.getTitle()).equals(Utils.removeAccents(bookTitle))).findFirst().get();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getBookById(Integer bookId) {
        String bookTitle = "";
        try {
            Book book = books.stream().filter(b -> b.getId() == bookId).findFirst().get();
            bookTitle = book.getTitle();
        } catch (Exception error) {
        }

        return bookTitle;
    }
}
