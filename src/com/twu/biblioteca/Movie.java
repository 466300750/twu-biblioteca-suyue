package com.twu.biblioteca;

public class Movie {
    private String name;
    private int year;
    private String director;
    private float rate;

    public Movie(String name, int year, String director, float rate) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", rate=" + rate +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public float getRate() {
        return rate;
    }
}
