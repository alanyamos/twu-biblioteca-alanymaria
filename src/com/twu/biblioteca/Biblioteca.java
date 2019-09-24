package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class Biblioteca {

    private PrintStream printStream;
    private List<Book> books;
    private Constants constants = new Constants();

    public Biblioteca(PrintStream printStream, List<Book> books) {
        this.printStream = printStream;
        this.books = books;
    }

    public void printWelcomeMessage() {
        String welcomeMessage = constants.welcomeMessage;
        printStream.println(welcomeMessage);
    }

    public void listAllBooks() {
        String listOfBooks =  "";

        for (Book book : books) {
            listOfBooks += "Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nYear published: " + book.getYearPublished() + "\n--------------------\n\n";
        }

        printStream.println(listOfBooks);

    }
}
