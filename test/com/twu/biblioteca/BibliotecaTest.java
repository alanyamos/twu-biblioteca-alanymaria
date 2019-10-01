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

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        books = new ArrayList<Book>();
        books.add(new Book("Book Title 1", "0000", "Author 1", "not reserved"));
        books.add(new Book("Book Title 2", "0000", "Author 2", "not reserved"));
        books.add(new Book("Book Title 3", "0000", "Author 3", "not reserved"));
        biblioteca = new Biblioteca(printStream, books, bufferedReader);
        biblioteca.factory();
    }

    @Test
    public void shouldPrintAWelcomeMessageWhenApplicationStarts() {
        String expectedMessage = Mocks.expectedMessage;
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
        String expectedListOfBooks = Mocks.expectedListOfBooks;
        biblioteca.optionHandler("1");
        verify(printStream).println(expectedListOfBooks);
    }

    @Test
    public void shouldPrintAWarningMessageWhenAInvalidOptionIsChosenFromMenu() {
        String expectedInvalidOptionMessage = Mocks.expectedInvalidOptionMessage;
        String invalidOption = Mocks.invalidOption;
        biblioteca.optionHandler(invalidOption);
        verify(printStream).println(expectedInvalidOptionMessage);
    }

    @Test
    public void shouldCheckoutABookWhenOptionTwoIsChosenFromMenu() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Book Title 1");
        Book book = null;
        for (Book b : books) {
            if (b.getTitle().equals("Book Title 1")) {
                book = b;
            }
        }
        biblioteca.optionHandler("2");
        assertThat(book.getStatus(), is("reserved"));
    }
}

