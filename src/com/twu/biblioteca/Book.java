package com.twu.biblioteca;

public class Book {
    private String name;
    private String author;
    private int publicDate;

    public Book(String name, String author, int publicDate) {
        this.name = name;
        this.author = author;
        this.publicDate = publicDate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicDate() {
        return publicDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publicDate=" + publicDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (publicDate != book.publicDate) return false;
        if (!name.equals(book.name)) return false;
        return author.equals(book.author);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + publicDate;
        return result;
    }
}
