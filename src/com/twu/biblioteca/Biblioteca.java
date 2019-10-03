package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
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
    private Map<String, Command> options = new HashMap<String, Command>();

    public Biblioteca(PrintStream printStream, BufferedReader bufferedReader, BookService bookService, MovieService movieService) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.bookService = bookService;
        this.movieService = movieService;
    }

    public void welcomeMessage() {
        String welcomeMessage = Constants.welcomeMessage;
        printStream.println(welcomeMessage);
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
            public void runCommand() { exit(); };
        });
    }

    public void optionHandler(String option) {
        try {
            options.get(option).runCommand();
        } catch (Exception error) {
            String invalidOptionMessage = Constants.invalidOptionMessage;
            printStream.println(invalidOptionMessage);
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
            bookService.update(bookTitle, "reserved");
            printStream.println("Thank you! Enjoy the book.\n");
        } catch (UpdateException error) {
            printStream.println("Sorry, that book is not available.\n");
        }
    }

    private void returnBook() {
        printStream.println("Which book do you want to return?");
        String bookTitle = readLine();
        try {
            bookService.update(bookTitle, "not reserved");
            printStream.println("Thank you for returning the book.\n");
        } catch (UpdateException error) {
            printStream.println("That is not a valid book to return.\n");
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
            movieService.update(movieTitle, "reserved");
            printStream.println("Thank you! Enjoy the movie.\n");
        } catch (UpdateException error) {
            printStream.println("Sorry, that movie is not available.\n");
        }
    }

    private void exit() {
        System.exit(0);
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
