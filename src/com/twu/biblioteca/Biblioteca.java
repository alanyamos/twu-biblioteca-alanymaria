package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Biblioteca {

    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private List<Book> books;
    private Constants constants = new Constants();

    public Biblioteca(PrintStream printStream, List<Book> books, BufferedReader bufferedReader) {
        this.printStream = printStream;
        this.books = books;
        this.bufferedReader = bufferedReader;
    }

    public void printWelcomeMessage() {
        String welcomeMessage = constants.welcomeMessage;
        printStream.println(welcomeMessage);
    }

    public void menu() {
        String menu = constants.menu;
        printStream.println(menu);
        String option = readLine();

        switch (Integer.parseInt(option)) {
            case 1:
                listAllBooks();
                break;
            default:
                printInvalidOptionMessage();
                break;
        }
    }


    public void listAllBooks() {
        String listOfBooks =  "";

        for (Book book : books) {
            listOfBooks += "Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nYear published: " + book.getYearPublished() + "\n--------------------\n\n";
        }

        printStream.println(listOfBooks);
    }

    private void printInvalidOptionMessage() {
        String invalidOptionMessage = constants.invalidOptionMessage;
        printStream.println(invalidOptionMessage);
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
