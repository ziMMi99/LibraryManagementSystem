package com.example.portfolioapp.controllers.libraryController;

import com.example.portfolioapp.dbo.Book;
import com.example.portfolioapp.dbo.BookStore;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    BookStore bookStore = new BookStore();

    private static Book bookChoice;

    @FXML
    ListView<Book> SearchList;
    @FXML
    TextField SearchField;


    public void setSearchButtonAction() {
        ObservableList<Book> foundBooks = bookStore.search(SearchField.getText());
        SearchList.setItems(foundBooks);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            SearchList.getItems().addAll(bookStore.getBooks());
        } catch (Exception e) {
            System.out.println("Could not add books to list view");
        }

        SearchList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> arg0, Book arg1, Book arg2) {

                bookChoice = SearchList.getSelectionModel().getSelectedItem();
                System.out.println(bookChoice);
            }
        });

    }
}
