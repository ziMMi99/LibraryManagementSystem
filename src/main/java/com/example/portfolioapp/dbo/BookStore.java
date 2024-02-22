package com.example.portfolioapp.dbo;

import com.example.portfolioapp.data.DataLayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class BookStore {
    private final ObservableList<Book> books;
    DataLayer data = new DataLayer();

    public BookStore() {
        // Initialize with some sample books
        books = data.getAllBooks();
    }

    public ObservableList<Book> search(String keyword) {
        ObservableList<Book> searchResults = FXCollections.observableArrayList();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getGenre().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }

    public ObservableList<Book> getBooks() {
        return books;
    }

    public boolean checkAvailability() {
        return true;
    }

    public Book borrowBook(Book book) {
        return book;
    }
}
