package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Machine {

    private Scanner scanner = new Scanner(System.in);
    private BookAndMovieDatabase bookAndMovieDatabase;
    private List<Book> sameNameBookList = new ArrayList<Book>();
    private boolean loginSuccess;
    private User currentUser;

    public Machine(BookAndMovieDatabase bookAndMovieDatabase) {
        this.bookAndMovieDatabase = bookAndMovieDatabase;
    }

    public void display() {
        System.out.println(bookAndMovieDatabase.generateWelcomeMessage());
        displaySelectMenuOption(bookAndMovieDatabase.getMenuList());
    }

    public String selectMenuOption(int i) {
        int optionCount = bookAndMovieDatabase.getMenuList().size();
        if(i>0 && i<=optionCount) {
            return bookAndMovieDatabase.getMenuList().get(i-1);
        } else {
            return "Select a valid option!";
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

        switch (selectOptionResult) {
            case "Login":
                login();
                break;
            case "UserInfo":
                displayUserInfo(currentUser);
                break;
            case "ListBooks":
                index = 1;
                for(Book book : bookAndMovieDatabase.getBookList()) {
                    System.out.println(index+". "+book);
                    index++;
                }
                break;
            case "ListMovies":
                index = 1;
                for(Movie movie : bookAndMovieDatabase.getMovieList()) {
                    System.out.println(index+". "+movie);
                    index++;
                }
                break;
            case "Check_outBook":
                displayCheckOutBook();
                break;
            case "ReturnBook":
                displayReturnBook();
                break;
            case "Check_outMovie":
                displayCheckOutMovie();
                break;
            case "ReturnMovie":
                displayReturnMovie();
                break;
            case "Logout":
                logout();
                break;
            case "Quit":
                displayQuit();
                return;
            default:
                System.out.println(selectOptionResult);
        }
        displaySelectMenuOption(menuList);
    }



    public String checkOutBook(String bookName) {
        sameNameBookList.clear();
        Book checkedOutBook = null;
        for(Book book : bookAndMovieDatabase.getBookList()) {
            if(book.getName().equals(bookName)) {
                sameNameBookList.add(book);
            }
        }
        if(sameNameBookList.size() == 1) {
            checkedOutBook = sameNameBookList.get(0);
            bookAndMovieDatabase.getCheckedOutBookList().add(checkedOutBook);
            bookAndMovieDatabase.getBookList().remove(checkedOutBook);
            return "Thank you! Enjoy the book.";
        } else if(sameNameBookList.size() > 1) {
             return "There are more than one books in the same name!";
        } else {
            return "That book is not available.";
        }
    }

    public String checkOutMovie(String movieName) {
        Movie checkedOutMovie = null;
        for(Movie movie : bookAndMovieDatabase.getMovieList()) {
            if(movie.getName().equals(movieName)) {
                checkedOutMovie = movie;
                break;
            }
        }
        if(checkedOutMovie != null) {
            bookAndMovieDatabase.getCheckedOutMovieList().add(checkedOutMovie);
            bookAndMovieDatabase.getMovieList().remove(checkedOutMovie);
            return "Thank you! Enjoy the movie.";
        } else {
            return "That movie is not available.";
        }
    }

    public String returnBook(String bookName) {
        boolean found = false;
        Book returnBook = null;
        for(Book book : bookAndMovieDatabase.getCheckedOutBookList()) {
            if(book.getName().equals(bookName)) {
                found = true;
                returnBook = book;
                break;
            }
        }
        if(found) {
            bookAndMovieDatabase.getCheckedOutBookList().remove(returnBook);
            bookAndMovieDatabase.getBookList().add(returnBook);
            return "Thank you for returning the book.";
        } else {
            return "That is not a valid book to return.";
        }
    }

    public String returnMovie(String movieName) {
        boolean found = false;
        Movie returnMovie = null;
        for(Movie movie : bookAndMovieDatabase.getCheckedOutMovieList()) {
            if(movie.getName().equals(movieName)) {
                found = true;
                returnMovie = movie;
                break;
            }
        }
        if(found) {
            bookAndMovieDatabase.getCheckedOutMovieList().remove(returnMovie);
            bookAndMovieDatabase.getMovieList().add(returnMovie);
            return "Thank you for returning the movie.";
        } else {
            return "That is not a valid movie to return.";
        }
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

    private void displayCheckOutMovie() {
        System.out.println("Please input the movie's name you want to check out:");
        String movieName = scanner.nextLine();
        String checkOutResult = checkOutMovie(movieName);
        System.out.println(checkOutResult);
        if(checkOutResult.equals("That movie is not available.")) {
            displayCheckOutMovie();
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
            bookAndMovieDatabase.getCheckedOutBookList().add(checkedOutBook);
            bookAndMovieDatabase.getBookList().remove(checkedOutBook);
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

    private void displayReturnMovie() {
        System.out.println("Please input the movie's name you want to return:");
        String movieName = scanner.nextLine();
        String returnResult = returnMovie(movieName);
        System.out.println(returnResult);
        if(!returnResult.equals("Thank you for returning the movie.")) {
            displayReturnMovie();
        }
    }

    private void displayQuit() {
        System.out.println("Bye Bye!");
        scanner.close();
    }

    public void login() {
        String userLibraryNumber = "";
        String userPassword = "";
        System.out.println("Please input your library number: ");
        userLibraryNumber = scanner.nextLine();
        System.out.println("Please input your password:");
        userPassword = scanner.nextLine();
        loginSuccess = verifyPassword(userLibraryNumber, userPassword);
        handleLogin();
    }

    public void handleLogin() {
        if(loginSuccess) {
            bookAndMovieDatabase.getMenuList().remove("Login");
            bookAndMovieDatabase.getMenuList().add(0, "UserInfo");
            bookAndMovieDatabase.getMenuList().remove("Quit");
            bookAndMovieDatabase.getMenuList().add("Check_outBook");
            bookAndMovieDatabase.getMenuList().add("ReturnBook");
            bookAndMovieDatabase.getMenuList().add("Check_outMovie");
            bookAndMovieDatabase.getMenuList().add("ReturnMovie");
            bookAndMovieDatabase.getMenuList().add("Logout");
            bookAndMovieDatabase.getMenuList().add("Quit");
            currentUser = new User("Meghan", "Meghan@163.com", "13546557895");
        }
    }

    private boolean verifyPassword(String accout, String password) {
        return true;
    }

    private void logout() {
        if(loginSuccess) {
            loginSuccess = false;
            bookAndMovieDatabase.getMenuList().remove("UserInfo");
            bookAndMovieDatabase.getMenuList().add(0, "Login");
            bookAndMovieDatabase.getMenuList().remove("Check_outBook");
            bookAndMovieDatabase.getMenuList().remove("ReturnBook");
            bookAndMovieDatabase.getMenuList().remove("Check_outMovie");
            bookAndMovieDatabase.getMenuList().remove("ReturnMovie");
            bookAndMovieDatabase.getMenuList().remove("Logout");
        }
    }

    public void displayUserInfo(User user) {
        System.out.println("User information: " + user);
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }
}
