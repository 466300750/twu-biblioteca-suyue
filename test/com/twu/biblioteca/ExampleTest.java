package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTest {
    BibliotecaApp bibliotecaApp;

    @Before
    public void before() throws Exception {
        bibliotecaApp = new BibliotecaApp();
    }

    @Test
    public void testDisplayWelcome() throws Exception {
        assertEquals("Welcome to Biblioteca!", bibliotecaApp.generateWelcomeMessage());
    }

    @Test
    public void testDisplayMenu() {
        assertEquals(4, bibliotecaApp.getMenuList().size());
    }

    @Test
    public void testDisplayBookLists() throws Exception {
        assertEquals(2, bibliotecaApp.getBookList().size());
    }

    @Test
    public void testSelectMenuOptionCorrect() throws Exception {
        assertEquals("List Books", bibliotecaApp.selectMenuOption(1));
    }

    @Test
    public void testSelectMenuOptionWrong() throws Exception {
        assertEquals("Select a valid option!", bibliotecaApp.selectMenuOption(5));
    }

    @Test
    public void testCheckOutBookSuccess() throws Exception {
        assertEquals("Thank you! Enjoy the book.", bibliotecaApp.checkOutBook("Head First Java"));
    }

    @Test
    public void testCheckOutBookFail() throws Exception {
        assertEquals("That book is not available.", bibliotecaApp.checkOutBook("Head First java"));
    }

    @Test
    public void testReturnBookSuccess() throws Exception {
        bibliotecaApp.checkOutBook("Head First Java");
        assertEquals("Thank you for returning the book.", bibliotecaApp.returnBook("Head First Java"));
    }

    @Test
    public void testReturnBookFail() throws Exception {
        bibliotecaApp.checkOutBook("Head First Java");
        assertEquals("That is not a valid book to return.", bibliotecaApp.returnBook("Head First java"));
    }
}
