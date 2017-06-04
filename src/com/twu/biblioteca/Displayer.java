package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class Displayer {

    private Scanner scanner = new Scanner(System.in);
    private LibraryBuilder libraryBuilder;

    public Displayer(LibraryBuilder libraryBuilder) {
        this.libraryBuilder = libraryBuilder;
    }

    public void display() {
        System.out.println(libraryBuilder.generateWelcomeMessage());
        displaySelectMenuOption(libraryBuilder.getMenuList());
    }


    private void displaySelectMenuOption(List<String> menuList) {
        System.out.println("Menu Option: ");
        int index = 1;
        for(String menuOption : menuList) {
            System.out.println(index + ". "+ menuOption);
            index++;
        }
        System.out.println("Please input your select number: ");
        int selectNumber = 0;
        try {
            selectNumber = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please input number!");
            displaySelectMenuOption(menuList);
        }

        String selectOptionResult = libraryBuilder.selectMenuOption(selectNumber);
        if(selectOptionResult.equals("List Books")) {
            index = 1;
            for(Book book : libraryBuilder.getBookList()) {
                System.out.println(index+". "+book);
            }
            index++;
        } else if(selectOptionResult.equals("Check-out Book")) {
            displayCheckOutBook();
        } else if(selectOptionResult.equals("Return Book")) {
            displayReturnBook();
        } else if(selectOptionResult.equals("Quit")) {
            displayQuit();
            return;
        } else {
            System.out.println(selectOptionResult);
        }
        displaySelectMenuOption(menuList);
    }

    private void displayCheckOutBook() {
        System.out.println("Please input the book's name you want to check out:");
        String bookName = scanner.nextLine();
        String checkOutResult = libraryBuilder.checkOutBook(bookName);
        System.out.println(checkOutResult);
        if(!checkOutResult.equals("Thank you! Enjoy the book.")) {
            displayCheckOutBook();
        }
    }

    private void displayReturnBook() {
        System.out.println("Please input the book's name you want to return:");
        String bookName = scanner.nextLine();
        String returnResult = libraryBuilder.returnBook(bookName);
        System.out.println(returnResult);
        if(!returnResult.equals("Thank you for returning the book.")) {
            displayReturnBook();
        }
    }

    private void displayQuit() {
        System.out.println("Bye Bye!");
        scanner.close();
    }
}
