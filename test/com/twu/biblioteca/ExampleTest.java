package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTest {
    LibraryBuilder libraryBuilder;

    @Before
    public void before() throws Exception {
        libraryBuilder = new LibraryBuilder();
    }

    @Test
    public void testDisplayWelcome() throws Exception {
        assertEquals("Welcome to LibraryBuilder!", libraryBuilder.generateWelcomeMessage());
    }

    @Test
    public void testDisplayMenu() {
        assertEquals(4, libraryBuilder.getMenuList().size());
    }

    @Test
    public void testDisplayBookLists() throws Exception {
        assertEquals(2, libraryBuilder.getBookList().size());
    }

    @Test
    public void testSelectMenuOptionCorrect() throws Exception {
        assertEquals("List Books", libraryBuilder.selectMenuOption(1));
    }

    @Test
    public void testSelectMenuOptionWrong() throws Exception {
        assertEquals("Select a valid option!", libraryBuilder.selectMenuOption(5));
    }

    @Test
    public void testCheckOutBookSuccess() throws Exception {
        assertEquals("Thank you! Enjoy the book.", libraryBuilder.checkOutBook("Head First Java"));
    }

    @Test
    public void testCheckOutBookFail() throws Exception {
        assertEquals("That book is not available.", libraryBuilder.checkOutBook("Head First java"));
    }

    @Test
    public void testReturnBookSuccess() throws Exception {
        libraryBuilder.checkOutBook("Head First Java");
        assertEquals("Thank you for returning the book.", libraryBuilder.returnBook("Head First Java"));
    }

    @Test
    public void testReturnBookFail() throws Exception {
        libraryBuilder.checkOutBook("Head First Java");
        assertEquals("That is not a valid book to return.", libraryBuilder.returnBook("Head First java"));
    }
}
