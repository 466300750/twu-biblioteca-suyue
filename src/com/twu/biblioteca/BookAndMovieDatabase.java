package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BookAndMovieDatabase {
    private String welcomeMessage = "";
    private List<String> menuList = new ArrayList<String>();
    private List<Book> bookList = new ArrayList<Book>();
    private List<Book> checkedOutBookList = new ArrayList<Book>();
    private List<Movie> movieList = new ArrayList<Movie>();
    private List<Movie> checkedOutMovieList = new ArrayList<Movie>();


    public BookAndMovieDatabase() {
        welcomeMessage = generateWelcomeMessage();
        generateMenu();
        generateBookLists();
        generateMovieLists();
    }

    public String generateWelcomeMessage() {
        return "Welcome to BookAndMovieDatabase!";
    }

    public List<String> generateMenu() {
        menuList.add("Login");
        menuList.add("ListBooks");
        menuList.add("ListMovies");
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

    public List<Movie> generateMovieLists() {
        movieList.add(new Movie("Wonder Woman", 2017, "Patty Jenkins", (float) 7.3));
        movieList.add(new Movie("Trainspootting", 2017, "Danny Boyle", (float) 8.1));
        movieList.add(new Movie("Sing", 2016, "Garth Jennings", (float) 8.2));
        return movieList;
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

    public List<Movie> getMovieList() {
        return movieList;
    }

    public List<Movie> getCheckedOutMovieList() {
        return checkedOutMovieList;
    }
}
