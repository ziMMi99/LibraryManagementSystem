package com.example.portfolioapp.dbo;

public class Book {
    //Class variables
    private String title, author, genre;
    private int quantity, availability;

    //Constuctors
    public Book(String title, String author, String genre, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
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
        return availability;
    }
    //Getters
    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
