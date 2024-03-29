package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class BibliotecaTest {

    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private BookService bookService;
    private List<Book> books;
    private MovieService movieService;
    private List<Movie> movies;
    private UserService userService;
    private List<User> users;
    private Biblioteca biblioteca;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);

        books = new ArrayList<Book>(
            Arrays.asList(
                new Book(0, "Book Title 1", "0000", "Author 1", "not reserved"),
                new Book(1, "Book Title 2", "0000", "Author 2", "reserved"),
                new Book(2, "Book Title 3", "0000", "Author 3", "not reserved")
            )
        );

        bookService = new BookService();
        bookService.setBooks(books);

        movies = new ArrayList<Movie>(
            Arrays.asList(
                new Movie(0, "Movie Title 1", "0000", "Director 1", "0", "not reserved"),
                new Movie(1, "Movie Title 2", "0000", "Director 2", "0","reserved"),
                new Movie(2, "Movie Title 3", "0000", "Director 3", "0", "not reserved")
            )
        );

        movieService = new MovieService();
        movieService.setMovies(movies);

        users = new ArrayList<User>(
            Arrays.asList(
                new User(1, "User 1", "user1@email.com","0000000","111-1111", "111111", "unlogged"),
                new User(2, "User 2", "user2@email.com","0000000", "222-2222", "222222", "unlogged"),
                new User(3, "User 3", "user3@email.com","0000000", "333-3333", "333333", "logged")
            )
        );

        userService = new UserService();
        userService.setUsers(users);

        biblioteca = new Biblioteca(printStream, bufferedReader, bookService, movieService, userService);
        biblioteca.factory();
    }

    @Test
    public void shouldPrintAWelcomeMessageWhenApplicationStarts() {
        String expectedMessage = Mocks.expectedMessage;
        biblioteca.welcomeMessage();
        verify(printStream).println(expectedMessage);
    }

    @Test
    public void shouldLoginWhenPasswordAndNumberAreCorrects() throws IOException {
        when(bufferedReader.readLine()).thenReturn("333-3333", "333333");
        User user = users.get(2);
        biblioteca.login();
        assertThat(user.getStatus(), is("logged"));
    }

    @Test
    public void shouldNotLoginWhenPasswordIsWrong() throws IOException {
        when(bufferedReader.readLine()).thenReturn("111-1111", "111110");
        biblioteca.login();
        verify(printStream).println("Invalid number or password. Please, try again!\n");
    }

    @Test
    public void shouldNotLoginWhenNumberIsWrong() throws IOException {
        when(bufferedReader.readLine()).thenReturn("Movie Title 1", "111-1110", "111111");
        biblioteca.login();
        verify(printStream).println("Invalid number or password. Please, try again!\n");
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
    public void shouldNotReturnABookWhenOptionThreeIsChosenFromMenuAndThereIsASpellingError() throws IOException {
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

    @Test
    public void shouldShowUserProfileWhenOptionSixIsChosenFromMenu() {
        String expectedInfo = Mocks.expectedInfo;
        biblioteca.optionHandler("6");
        verify(printStream).println(expectedInfo);
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldLeaveTheAppWhenOptionSevenIsChosenFromMenu() {
        biblioteca.optionHandler("7");
        exit.expectSystemExitWithStatus(0);
    }
}
