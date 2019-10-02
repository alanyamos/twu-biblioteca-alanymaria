package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Command {
    void runCommand();
}

public class Biblioteca {

    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private List<Book> books;
    private List<Movie> movies;
    private Map<String, Command> options = new HashMap<String, Command>();

    public Biblioteca(PrintStream printStream, List<Book> books, List<Movie> movies, BufferedReader bufferedReader) {
        this.printStream = printStream;
        this.books = books;
        this.movies = movies;
        this.bufferedReader = bufferedReader;
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

        for (Book book : books) {
            if (isAvailable(book)) {
                listOfBooks += "Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nYear published: " + book.getYearPublished() + "\n--------------------\n\n";
            }
        }

        printStream.println(listOfBooks);
    }

    private void checkoutBook () {
        printStream.println("Which book do you want to checkout?");
        String bookTitle = readLine();
        boolean failedCheckout = true;

        for (Book book : books) {
            if (removeAccents(book.getTitle()).equals(removeAccents(bookTitle)) && isAvailable(book)) {
                book.setStatus("reserved");
                failedCheckout = false;
                printStream.println("Thank you! Enjoy the book.\n");
            }
        }

        if (failedCheckout) {
            printStream.println("Sorry, that book is not available.\n");
        }
    }

    private void returnBook() {
        printStream.println("Which book do you want to return?");
        String bookTitle = readLine();
        boolean failedReturn = true;

        for (Book book : books) {
            if (removeAccents(book.getTitle()).equals(removeAccents(bookTitle)) && !isAvailable(book)) {
                book.setStatus("not reserved");
                failedReturn = false;
                printStream.println("Thank you for returning the book.\n");
            }
        }

        if (failedReturn) {
            printStream.println("That is not a valid book to return.\n");
        }
    }

    private void listMovies() {
        String listOfMovies =  "";

        for (Movie movie : movies) {
            if (isAvailable(movie)) {
                listOfMovies += "Title: " + movie.getTitle() + "\nDirector: " + movie.getDirector() + "\nYear: " + movie.getYear() + "\nRating: " + movie.getRating() + "\n--------------------\n\n";
            }
        }

        printStream.println(listOfMovies);
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

    private String removeAccents(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }

    private boolean isAvailable(IsAvailable content) {
        return content.getStatus().equals(Constants.notReserved);
    }
}
