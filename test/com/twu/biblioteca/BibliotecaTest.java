package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BibliotecaTest {

    private PrintStream printStream;
    private Biblioteca biblioteca;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        biblioteca = new Biblioteca(printStream);
    }

    @Test
    public void shouldPrintAWelcomeMessageWhenApplicationStarts() {
        String expectedMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        biblioteca.printWelcomeMessage();
        verify(printStream).println(expectedMessage);
    }
}

