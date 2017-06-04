package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class LibraryBuilder {
    private String welcomeMessage = "";
    private List<String> menuList = new ArrayList<String>();
    private List<Book> bookList = new ArrayList<Book>();
    private List<Book> checkedOutBookList = new ArrayList<Book>();

    public LibraryBuilder() {
        welcomeMessage = generateWelcomeMessage();
        generateMenu();
        generateBookLists();
    }

    public String generateWelcomeMessage() {
        return "Welcome to LibraryBuilder!";
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
        return bookList;
    }

    public String selectMenuOption(int i) {
        int optionCount = menuList.size();
        if(i>0 && i<=optionCount) {
            return menuList.get(i-1);
        } else {
            return "Select a valid option!";
        }
    }

    public String checkOutBook(String bookName) {
        boolean found = false;
        Book checkedOutBook = null;
        for(Book book : bookList) {
            if(book.getName().equals(bookName)) {
                found = true;
                checkedOutBook = book;
                break;
            }
        }
        if(found) {
            checkedOutBookList.add(checkedOutBook);
            bookList.remove(checkedOutBook);
            return "Thank you! Enjoy the book.";
        } else {
            return "That book is not available.";
        }
    }

    public String returnBook(String bookName) {
        boolean found = false;
        Book returnBook = null;
        for(Book book : checkedOutBookList) {
            if(book.getName().equals(bookName)) {
                found = true;
                returnBook = book;
                break;
            }
        }
        if(found) {
            checkedOutBookList.remove(returnBook);
            bookList.add(returnBook);
            return "Thank you for returning the book.";
        } else {
            return "That is not a valid book to return.";
        }
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
