package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BibliotecaTest {

    private PrintStream printStream;
    private Biblioteca biblioteca;
    private List<Book> books;
    private Mocks mocks;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        books = new ArrayList<Book>();
        biblioteca = new Biblioteca(printStream, books);
        mocks = new Mocks();
    }

    @Test
    public void shouldPrintAWelcomeMessageWhenApplicationStarts() {
        String expectedMessage = mocks.expectedMessage;
        biblioteca.printWelcomeMessage();
        verify(printStream).println(expectedMessage);
    }

    @Test
    public void shouldPrintAListOfAllBooksAfterWelcomeMessage() {
        String expectedListOfBooks = mocks.expectedListOfBooks;

        books.add(new Book("Book Title 1"));
        books.add(new Book("Book Title 2"));
        books.add(new Book("Book Title 3"));

        biblioteca.listAllBooks();
        verify(printStream).println(expectedListOfBooks);
    }
}

