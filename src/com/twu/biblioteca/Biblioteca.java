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
    private Map<String, Command> options = new HashMap<String, Command>();

    public Biblioteca(PrintStream printStream, List<Book> books, BufferedReader bufferedReader) {
        this.printStream = printStream;
        this.books = books;
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
            if (book.getStatus() !=  Constants.reserved) {
                listOfBooks += "Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nYear published: " + book.getYearPublished() + "\n--------------------\n\n";
            }
        }

        printStream.println(listOfBooks);
    }

    private void checkoutBook () {
        printStream.println("Which book do you want to checkout?");
        String bookTitle = readLine();
        for (Book book : books) {
            if (removeAccents(book.getTitle()).equals(removeAccents(bookTitle))) {
                book.setStatus("reserved");
            }
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

    public static String removeAccents(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }
}
