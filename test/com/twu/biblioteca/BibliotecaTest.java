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
    private List<Movie> movies;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);

        books = new ArrayList<Book>();
        books.add(new Book("Book Title 1", "0000", "Author 1", "not reserved"));
        books.add(new Book("Book Title 2", "0000", "Author 2", "reserved"));
        books.add(new Book("Book Title 3", "0000", "Author 3", "not reserved"));

        movies = new ArrayList<Movie>();
        movies.add(new Movie("Movie Title 1", "0000", "Director 1", "0", "not reserved"));
        movies.add(new Movie("Movie Title 2", "0000", "Director 2", "0","reserved"));
        movies.add(new Movie("Movie Title 3", "0000", "Director 3", "0", "not reserved"));

        biblioteca = new Biblioteca(printStream, books, movies, bufferedReader);
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
    public void shouldPrintAWarningMessageWhenAInvalidOptionIsChosenFromMenu() {
        String expectedInvalidOptionMessage = Mocks.expectedInvalidOptionMessage;
        String invalidOption = Mocks.invalidOption;
        biblioteca.optionHandler(invalidOption);
        verify(printStream).println(expectedInvalidOptionMessage);
    }

    @Test
    public void shouldPrintAListOfAvailableBooksWhenOptionOneIsChosenFromMenu() {
        String expectedListOfBooks = Mocks.expectedListOfBooks;
        biblioteca.optionHandler("1");
        verify(printStream).println(expectedListOfBooks);
    }

    @Test
    public void shouldCheckoutABookWhenOptionTwoIsChosenFromMenuAndTheBookIsAvailable() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Book Title 1");
        Book book = books.get(0);
        biblioteca.optionHandler("2");
        assertThat(book.getStatus(), is("reserved"));
    }

    @Test
    public void shouldNotCheckoutABookWhenOptionTwoIsChosenFromMenuAndTheBookIsNotAvailable() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Book Title 2");
        biblioteca.optionHandler("2");
        verify(printStream).println("Sorry, that book is not available.\n");
    }

    @Test
    public void shouldNotCheckoutABookWhenOptionTwoIsChosenFromMenuAndThereIsASpellingError() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Book Title 4");
        biblioteca.optionHandler("2");
        verify(printStream).println("Sorry, that book is not available.\n");
    }

    @Test
    public void shouldReturnABookWhenOptionThreeIsChosenFromMenu() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Book Title 2");
        Book book = books.get(1);
        biblioteca.optionHandler("3");
        assertThat(book.getStatus(), is("not reserved"));
    }

    @Test
    public void shouldNotReturnABookWhenOptionThreeIsChosenFromMenuAndTheBookIsAlreadyAvailable() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Book Title 1");
        biblioteca.optionHandler("3");
        verify(printStream).println("That is not a valid book to return.\n");
    }

    @Test
    public void shouldNotCheckoutABookWhenOptionThreeIsChosenFromMenuAndThereIsASpellingError() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Book Title 4");
        biblioteca.optionHandler("3");
        verify(printStream).println("That is not a valid book to return.\n");
    }

    @Test
    public void shouldPrintAListOfAvailableMoviesWhenOptionFourIsChosenFromMenu() {
        String expectedListOfMovies = Mocks.expectedListOfMovies;
        biblioteca.optionHandler("4");
        verify(printStream).println(expectedListOfMovies);
    }

    @Test
    public void shouldCheckoutAMovieWhenOptionFiveIsChosenFromMenuAndTheMovieIsAvailable() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Movie Title 1");
        Movie movie = movies.get(0);
        biblioteca.optionHandler("5");
        assertThat(movie.getStatus(), is("reserved"));
    }

    @Test
    public void shouldNotCheckoutAMovieWhenOptionFiveIsChosenFromMenuAndTheMovieIsNotAvailable() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Movie Title 2");
        biblioteca.optionHandler("5");
        verify(printStream).println("Sorry, that movie is not available.\n");
    }

    @Test
    public void shouldNotCheckoutAMovieWhenOptionFiveIsChosenFromMenuAndThereIsASpellingError() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Movie Title 4");
        biblioteca.optionHandler("5");
        verify(printStream).println("Sorry, that movie is not available.\n");
    }
}
