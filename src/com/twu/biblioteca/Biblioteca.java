package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Booking;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Command {
    void runCommand();
}

public class Biblioteca {

    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private BookService bookService;
    private MovieService movieService;
    private UserService userService;
    private List<Booking> bookings = new ArrayList<Booking>();
    private Map<String, Command> options = new HashMap<String, Command>();

    public Biblioteca(PrintStream printStream, BufferedReader bufferedReader, BookService bookService, MovieService movieService, UserService userService) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.bookService = bookService;
        this.movieService = movieService;
        this.userService = userService;
    }

    public void welcomeMessage() {
        String welcomeMessage = Constants.welcomeMessage;
        printStream.println(welcomeMessage);
    }

    public boolean login() {
        printStream.println("Login");

        printStream.println("Confirm your number:");
        String number = readLine();

        printStream.println("Confirm your password:");
        String password = readLine();

        boolean isLogged = false;

        try {
            userService.login(number, password);
            isLogged = true;
        } catch (Exception error) {
            printStream.println(error.getMessage());
        }

        return isLogged;
    }

    public String displayMenu() {
        String menu = Constants.menu;
        printStream.println(menu);

        return readLine();
    }

    public void factory() {
        options.put("1", new Command() {
            public void runCommand() { listBooks(); };
        });

        options.put("2", new Command() {
            public void runCommand() { checkoutBook(); };
        });

        options.put("3", new Command() {
            public void runCommand() { returnBook(); };
        });

        options.put("4", new Command() {
            public void runCommand() { listMovies(); };
        });

        options.put("5", new Command() {
            public void runCommand() { checkoutMovie(); };
        });

        options.put("6", new Command() {
            public void runCommand() { listReservations(); };
        });

        options.put("7", new Command() {
            public void runCommand() { exit(); };
        });
    }

    public void optionHandler(String option) {
        try {
            options.get(option).runCommand();
        } catch (Exception error) {
            printStream.println(Constants.invalidOptionMessage);
        }
    }

    private void listBooks() {
        String listOfBooks =  "";
        List<Book> books = bookService.listAll();

        for (Book book : books) {
            if (Utils.isAvailable(book, "reserved")) {
                listOfBooks += "Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nYear published: " + book.getYearPublished() + "\n--------------------\n\n";
            }
        }

        printStream.println(listOfBooks);
    }

    private void checkoutBook () {
        printStream.println("Which book do you want to checkout?");
        String bookTitle = readLine();

        try {
            User user = userService.getLoggedUser();
            bookService.checkoutBook(bookTitle, "reserved");
            Book book = bookService.getByTitle(bookTitle);
            Booking booking = new Booking(user.getId(), book.getId(), null);
            addBooking(booking);
            printStream.println("Thank you! Enjoy the book.\n");
        } catch (Exception error) {
            printStream.println(error.getMessage());
        }
    }

    private void returnBook() {
        printStream.println("Which book do you want to return?");
        String bookTitle = readLine();

        try {
            User user = userService.getLoggedUser();
            bookService.returnBook(bookTitle, "not reserved");
            Book book = bookService.getByTitle(bookTitle);
            Booking booking = new Booking(user.getId(), book.getId(), null);
            removeBooking(booking);
            printStream.println("Thank you for returning the book!\n");
        } catch (Exception error) {
            printStream.println(error.getMessage());
        }
    }

    private void listMovies() {
        String listOfMovies =  "";
        List<Movie> movies = movieService.listAll();

        for (Movie movie : movies) {
            if (Utils.isAvailable(movie, "reserved")) {
                listOfMovies += "Title: " + movie.getTitle() + "\nDirector: " + movie.getDirector() + "\nYear: " + movie.getYear() + "\nRating: " + movie.getRating() + "\n--------------------\n\n";
            }
        }

        printStream.println(listOfMovies);
    }

    private void checkoutMovie() {
        printStream.println("Which movie do you want to checkout?");
        String movieTitle = readLine();

        try {
            User user = userService.getLoggedUser();
            movieService.checkoutMovie(movieTitle, "reserved");
            Movie movie = movieService.getByTitle(movieTitle);
            Booking booking = new Booking(user.getId(), null, movie.getId());
            addBooking(booking);
            printStream.println("Thank you! Enjoy the movie.\n");
        } catch (Exception error) {
            printStream.println(error.getMessage());
        }
    }

    private void listReservations() {
        String listOfReservations =  "";

        for (Booking booking : bookings) {
            String bookTitle = bookService.getBookById(booking.getBook());
            String movieTitle = movieService.getMovieById(booking.getMovie());
            String content = bookTitle + "\n" + movieTitle;
            String user = userService.getUserById(booking.getUser());
            listOfReservations += "Owner: " + user + "\nTitle: " + content + "\n--------------------\n\n";
        }

        printStream.println(listOfReservations);
    }

    private void exit() {
        System.exit(0);
    }

    private void addBooking(Booking booking) {
        bookings.add(booking);
    }

    private void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    private String readLine() {
        String option = null;
        try {
            option = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return option;
    }

}
