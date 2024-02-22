package com.example.portfolioapp.data;

import com.example.portfolioapp.dbo.Book;
import com.example.portfolioapp.dbo.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DataLayer {
    private Connection connection;

    //Constructor for the datalayer, which initializes the JDBC driver loader, and opens the connection to the database
    public DataLayer() {
        loadJdbcDriver();
        openConnection();
    }
    //Loads the JDBC driver.
    private void loadJdbcDriver() {
        try {
            System.out.println("Loading JDBC driver...");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            System.out.println("JDBC driver loaded");

        } catch (ClassNotFoundException e) {
            System.out.println("Could not load JDBC driver!");
        }
    }
    //Opens a connection to the database
    private void openConnection() {
        try {
            String connectionString =
                    "jdbc:sqlserver://localhost:1433;" +
                            "instanceName=SQLEXPRESS;" +
                            "databaseName=" + "MyPortfolioDB" + ";" +
                            "integratedSecurity=true;" +
                            "trustServerCertificate=true;";

            System.out.println(connectionString);

            System.out.println("Connecting to the database...");

            connection = DriverManager.getConnection(connectionString);

            System.out.println("Connected to the database");

        } catch (SQLException e) {

            System.out.println("Could not connect to database: " + "MyPortfolioDB");
            System.out.println("Could not connect to the database: " + "MyPortfolioDB");

            System.out.println(e.getMessage());
        }
    }

    public void userRegistration(User user) {

        String sql = "INSERT INTO Users (username, hashedPassword, email, firstname, lastname, isAdmin) VALUES (?, ?, ?, ?, ?, 0)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstname());
            statement.setString(5, user.getLastname());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not register user - " + e.getMessage());
        }
    }

    public String getHashedPassword(String usernameToFind) {
        String sql = "SELECT hashedPassword FROM Users WHERE username = '" +  usernameToFind + "'";
        String hashedPassword = null;
        try (Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                hashedPassword = resultSet.getString("hashedPassword");
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve hashed password from database - " + e.getMessage());
        }
        return hashedPassword;
    }

    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed");
        }
    }

    public ArrayList<String> getAllUsernames () {
        String sql = "SELECT username FROM Users";
        ArrayList<String> usernames = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String usernameToAdd = resultSet.getString("username");
                usernames.add(usernameToAdd);
            }

        } catch (SQLException e) {
            System.out.println("Could not retrieve data from database - " + e.getMessage());
        }

        return usernames;
    }

    public ObservableList<Book> getAllBooks () {

        ObservableList<Book> books = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Book";

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(title, author, genre, quantity);

                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve books from database");
        }

        return books;
    }

    public ArrayList<User> getAllUsers () {

        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");

                User user = new User(username, email, firstname, lastname);

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve books from database");
        }

        return users;
    }
}
