package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BibliotecaTest {

    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Biblioteca biblioteca;
    private List<Book> books;
    private Mocks mocks;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        books = new ArrayList<Book>();
        books.add(new Book("Book Title 1", "0000", "Author 1"));
        books.add(new Book("Book Title 2", "0000", "Author 2"));
        books.add(new Book("Book Title 3", "0000", "Author 3"));
        biblioteca = new Biblioteca(printStream, books, bufferedReader);
        mocks = new Mocks();
    }

    @Test
    public void shouldPrintAWelcomeMessageWhenApplicationStarts() {
        String expectedMessage = mocks.expectedMessage;
        biblioteca.printWelcomeMessage();
        verify(printStream).println(expectedMessage);
    }

    @Test
    public void shouldPrintAListOfBooksWhenOptionOneIsChosenFromMenu() throws IOException {
        String expectedListOfBooks = mocks.expectedListOfBooks;
        when(bufferedReader.readLine()).thenReturn("1");
        biblioteca.displayMenu();
        verify(printStream).println(expectedListOfBooks);
    }

    @Test
    public void shouldPrintAWarningMessageWhenAInvalidOptionIsChosenFromMenu() throws IOException {
        String expectedInvalidOptionMessage = mocks.expectedInvalidOptionMessage;
        String invalidOption = mocks.invalidOption;
        when(bufferedReader.readLine()).thenReturn(invalidOption);
        biblioteca.displayMenu();
        verify(printStream).println(expectedInvalidOptionMessage);
    }
}

