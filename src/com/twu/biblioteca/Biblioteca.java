package com.twu.biblioteca;

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
    private List<Book> books;
    private Map<String, Command> options = new HashMap<String, Command>();
    private Constants constants = new Constants();

    public Biblioteca(PrintStream printStream, List<Book> books, BufferedReader bufferedReader) {
        this.printStream = printStream;
        this.books = books;
        this.bufferedReader = bufferedReader;
    }

    public void welcomeMessage() {
        String welcomeMessage = constants.welcomeMessage;
        printStream.println(welcomeMessage);
    }

    public String displayMenu() {
        String menu = constants.menu;
        printStream.println(menu);
        String option = readLine();

        return option;
    }

    public void factory() {
        options.put("1", new Command() {
            public void runCommand() { listBooks(); };
        });

        options.put("2", new Command() {
            public void runCommand() { exit(); };
        });
    }

    public void optionHandler(String option) {
        try {
            options.get(option).runCommand();
        } catch (Exception error) {
            String invalidOptionMessage = constants.invalidOptionMessage;
            printStream.println(invalidOptionMessage);
        }
    }


    private void listBooks() {
        String listOfBooks =  "";

        for (Book book : books) {
            listOfBooks += "Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nYear published: " + book.getYearPublished() + "\n--------------------\n\n";
        }

        printStream.println(listOfBooks);
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
