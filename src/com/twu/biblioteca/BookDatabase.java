package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BookDatabase {
    private String welcomeMessage = "";
    private List<String> menuList = new ArrayList<String>();
    private List<Book> bookList = new ArrayList<Book>();
    private List<Book> checkedOutBookList = new ArrayList<Book>();

    public BookDatabase() {
        welcomeMessage = generateWelcomeMessage();
        generateMenu();
        generateBookLists();
    }

    public String generateWelcomeMessage() {
        return "Welcome to BookDatabase!";
    }

    public List<String> generateMenu() {
        menuList.add("List Books");
        menuList.add("Check-out Book");
        menuList.add("Return Book");
        menuList.add("Quit");
        return menuList;
    }

    public List<Book> generateBookLists() {
        bookList.add(new Book("Test-driven Development: By Example", "Kent Beck", 2003));
        bookList.add(new Book("Head First Java", "Kathy Sierra, Bert Bates", 2005));
        bookList.add(new Book("The C Programming Language", "Brian W. Kernighan,Dennis M. Ritchie", 1988));
        bookList.add(new Book("The C Programming Language", "A. K. Goyal", 2007));
        return bookList;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public List<Book> getCheckedOutBookList() {
        return checkedOutBookList;
    }
}
