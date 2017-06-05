package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Machine {

    private Scanner scanner = new Scanner(System.in);
    private BookDatabase bookDatabase;
    List<Book> sameNameBookList = new ArrayList<Book>();

    public Machine(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    public void display() {
        System.out.println(bookDatabase.generateWelcomeMessage());
        displaySelectMenuOption(bookDatabase.getMenuList());
    }

    public String selectMenuOption(int i) {
        int optionCount = bookDatabase.getMenuList().size();
        if(i>0 && i<=optionCount) {
            return bookDatabase.getMenuList().get(i-1);
        } else {
            return "Select a valid option!";
        }
    }

    public String checkOutBook(String bookName) {
        sameNameBookList.clear();
        Book checkedOutBook = null;
        for(Book book : bookDatabase.getBookList()) {
            if(book.getName().equals(bookName)) {
                sameNameBookList.add(book);
            }
        }
        if(sameNameBookList.size() == 1) {
            checkedOutBook = sameNameBookList.get(0);
            bookDatabase.getCheckedOutBookList().add(checkedOutBook);
            bookDatabase.getBookList().remove(checkedOutBook);
            return "Thank you! Enjoy the book.";
        } else if(sameNameBookList.size() > 1) {
             return "There are more than one books in the same name!";
        } else {
            return "That book is not available.";
        }
    }

    public String returnBook(String bookName) {
        boolean found = false;
        Book returnBook = null;
        for(Book book : bookDatabase.getCheckedOutBookList()) {
            if(book.getName().equals(bookName)) {
                found = true;
                returnBook = book;
                break;
            }
        }
        if(found) {
            bookDatabase.getCheckedOutBookList().remove(returnBook);
            bookDatabase.getBookList().add(returnBook);
            return "Thank you for returning the book.";
        } else {
            return "That is not a valid book to return.";
        }
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

        String selectOptionResult = selectMenuOption(selectNumber);
        if(selectOptionResult.equals("List Books")) {
            index = 1;
            for(Book book : bookDatabase.getBookList()) {
                System.out.println(index+". "+book);
                index++;
            }
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
        String checkOutResult = checkOutBook(bookName);
        System.out.println(checkOutResult);
        if(checkOutResult.equals("That book is not available.")) {
            displayCheckOutBook();
        } else if(checkOutResult.equals("There are more than one books in the same name!")) {
            displaySameNameBooks(sameNameBookList);
        }
    }

    private void displaySameNameBooks(List<Book> sameNameBookList) {
        for(int i=0; i<sameNameBookList.size(); i++) {
            System.out.println((i+1) + ". " + sameNameBookList.get(i));
        }
        System.out.println("Please input the number of which one you want to check out: ");
        int selectNumber = 0;
        Book checkedOutBook = null;
        try {
            selectNumber = Integer.parseInt(scanner.nextLine());
            checkedOutBook = sameNameBookList.get(selectNumber-1);
            bookDatabase.getCheckedOutBookList().add(checkedOutBook);
            bookDatabase.getBookList().remove(checkedOutBook);
        }catch (NumberFormatException e){
            System.out.println("Please input number!");
            displaySameNameBooks(sameNameBookList);
        }
    }


    private void displayReturnBook() {
        System.out.println("Please input the book's name you want to return:");
        String bookName = scanner.nextLine();
        String returnResult = returnBook(bookName);
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
