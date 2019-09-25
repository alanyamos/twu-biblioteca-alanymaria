package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
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
        biblioteca.welcomeMessage();
        verify(printStream).println(expectedMessage);
    }

    @Test
    public void shouldReturnOneWhenOptionOneIsChosenFromMenu() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1");
        String option = biblioteca.displayMenu();
        assertThat(option, is("1"));
    }

    @Test
    public void shouldPrintAListOfBooksWhenOptionOneIsChosenFromMenu() {
        String expectedListOfBooks = mocks.expectedListOfBooks;
        biblioteca.factory();
        biblioteca.optionHandler( "1");
        verify(printStream).println(expectedListOfBooks);
    }

    @Test
    public void shouldPrintAWarningMessageWhenAInvalidOptionIsChosenFromMenu() {
        String expectedInvalidOptionMessage = mocks.expectedInvalidOptionMessage;
        String invalidOption = mocks.invalidOption;
        biblioteca.factory();
        biblioteca.optionHandler(invalidOption);
        verify(printStream).println(expectedInvalidOptionMessage);
    }
}

