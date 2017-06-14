package com.twu.biblioteca;


public class BibliotecaApp {

    private BookAndMovieDatabase bookAndMovieDatabase;
    private Machine machine;

    public BibliotecaApp() {
        bookAndMovieDatabase = new BookAndMovieDatabase();
        machine = new Machine(bookAndMovieDatabase);
    }


    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.getMachine().display();
    }

    public BookAndMovieDatabase getBookAndMovieDatabase() {
        return bookAndMovieDatabase;
    }

    public Machine getMachine() {
        return machine;
    }
}
