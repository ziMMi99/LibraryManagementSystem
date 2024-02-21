package com.example.portfolioapp.dbo;

public class Book {
    //Class variables
    private String title, author, genre;
    private int quantity, isAvailable;

    //Constuctors
    public Book(String title, String author, String genre, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return title + ", " + genre + ", " + author;
    }

    //Setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAvailability() {
        return isAvailable;
    }
    //Getters
    public void setAvailability(int isAvailable) {
        this.isAvailable = isAvailable;
    }
}
