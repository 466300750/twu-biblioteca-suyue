package com.twu.biblioteca;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private String welcomeMessage = "";
    private List<String> menuList = new ArrayList<String>();
    private List<Book> bookList = new ArrayList<Book>();
    private List<Book> checkedOutBookList = new ArrayList<Book>();


    public BibliotecaApp() {
        welcomeMessage = generateWelcomeMessage();
        generateMenu();
        generateBookLists();
    }


    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.display();
    }

    public String generateWelcomeMessage() {
        return "Welcome to Biblioteca!";
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
        if(i>0 && i<= optionCount) {
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

    private void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(welcomeMessage);
        displaySelectMenuOption(scanner);
    }


    private void displaySelectMenuOption(Scanner scanner) {
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
            displaySelectMenuOption(scanner);
        }

        String selectOptionResult = selectMenuOption(selectNumber);
        if(selectOptionResult.equals("List Books")) {
            index = 1;
            for(Book book : bookList) {
                System.out.println(index+". "+book);
            }
            index++;
        } else if(selectOptionResult.equals("Check-out Book")) {
            displayCheckOutBook(scanner);
        } else if(selectOptionResult.equals("Return Book")) {
            displayReturnBook(scanner);
        } else if(selectOptionResult.equals("Quit")) {
            displayQuit(scanner);
            return;
        } else {
            System.out.println(selectOptionResult);
        }
        displaySelectMenuOption(scanner);
    }

    private void displayCheckOutBook(Scanner scanner) {
        System.out.println("Please input the book's name you want to check out:");
        String bookName = scanner.nextLine();
        String checkOutResult = checkOutBook(bookName);
        System.out.println(checkOutResult);
        if(!checkOutResult.equals("Thank you! Enjoy the book.")) {
            displayCheckOutBook(scanner);
        }
    }

    private void displayReturnBook(Scanner scanner) {
        System.out.println("Please input the book's name you want to return:");
        String bookName = scanner.nextLine();
        String returnResult = returnBook(bookName);
        System.out.println(returnResult);
        if(!returnResult.equals("Thank you for returning the book.")) {
            displayReturnBook(scanner);
        }
    }

    private void displayQuit(Scanner scanner) {
        System.out.println("Bye Bye!");
        scanner.close();
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
