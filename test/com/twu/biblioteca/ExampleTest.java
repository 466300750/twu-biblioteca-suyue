package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleTest {
    BookAndMovieDatabase bookAndMovieDatabase;
    Machine machine;
    int bookCount = 4;
    int movieCount = 3;
    int menuCount = 4;

    @Before
    public void before() throws Exception {
        bookAndMovieDatabase = new BookAndMovieDatabase();
        machine = new Machine(bookAndMovieDatabase);
    }

    @Test
    public void testDisplayWelcome() throws Exception {
        assertEquals("Welcome to BookAndMovieDatabase!", bookAndMovieDatabase.generateWelcomeMessage());
    }

    @Test
    public void testDisplayMenu() {
        assertEquals(menuCount, bookAndMovieDatabase.getMenuList().size());
    }

    @Test
    public void testDisplayBookLists() throws Exception {
        assertEquals(bookCount, bookAndMovieDatabase.getBookList().size());
    }

    @Test
    public void testDisplayMovieLists() throws Exception {
        assertEquals(movieCount, bookAndMovieDatabase.getMovieList().size());
    }

    @Test
    public void testSelectMenuOptionCorrect() throws Exception {
        assertEquals("Login", machine.selectMenuOption(1));
    }

    @Test
    public void testSelectMenuOptionWrong() throws Exception {
        assertEquals("Select a valid option!", machine.selectMenuOption(5));
    }

    @Test
    public void testCheckOutBookSuccess() throws Exception {
        assertEquals("Thank you! Enjoy the book.", machine.checkOutBook("Head First Java"));
        assertEquals(bookCount-1, bookAndMovieDatabase.getBookList().size());
    }

    @Test
    public void testCheckOutBookFail() throws Exception {
        assertEquals("That book is not available.", machine.checkOutBook("Head First java"));
        assertEquals(bookCount, bookAndMovieDatabase.getBookList().size());
    }

    @Test
    public void testReturnBookSuccess() throws Exception {
        machine.checkOutBook("Head First Java");
        assertEquals(bookCount-1, bookAndMovieDatabase.getBookList().size());
        assertEquals("Thank you for returning the book.", machine.returnBook("Head First Java"));
        assertEquals(bookCount, bookAndMovieDatabase.getBookList().size());
    }

    @Test
    public void testReturnBookFail() throws Exception {
        machine.checkOutBook("Head First Java");
        assertEquals(bookCount-1, bookAndMovieDatabase.getBookList().size());
        assertEquals("That is not a valid book to return.", machine.returnBook("Head First java"));
        assertEquals(bookCount-1, bookAndMovieDatabase.getBookList().size());
    }

    @Test
    public void testLogin() throws Exception {
        assertEquals(menuCount, bookAndMovieDatabase.getMenuList().size());
        machine.setLoginSuccess(true);
        machine.handleLogin();
        assertEquals(9, bookAndMovieDatabase.getMenuList().size());
    }
}
