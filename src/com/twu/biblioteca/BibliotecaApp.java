package com.twu.biblioteca;


public class BibliotecaApp {

    private BookDatabase bookDatabase;
    private Machine machine;

    public BibliotecaApp() {
        bookDatabase = new BookDatabase();
        machine = new Machine(bookDatabase);
    }


    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.getMachine().display();
    }

    public BookDatabase getBookDatabase() {
        return bookDatabase;
    }

    public Machine getMachine() {
        return machine;
    }
}
