package com.twu.biblioteca;


public class BibliotecaApp {

    private LibraryBuilder libraryBuilder;
    private Displayer displayer;

    public BibliotecaApp() {
        libraryBuilder = new LibraryBuilder();
        displayer = new Displayer(libraryBuilder);
    }


    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.getDisplayer().display();
    }

    public LibraryBuilder getLibraryBuilder() {
        return libraryBuilder;
    }

    public Displayer getDisplayer() {
        return displayer;
    }
}
