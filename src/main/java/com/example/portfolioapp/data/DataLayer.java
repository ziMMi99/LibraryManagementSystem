package com.example.portfolioapp.data;

import com.example.portfolioapp.dbo.User;

import java.sql.*;
import java.util.ArrayList;

public class DataLayer {
    private Connection connection;

    //Constructor for the datalayer, which initializes the JDBC driver loader, and opens the connection to the database
    public DataLayer() {
        loadJdbcDriver();
        openConnection("MyPortfolioDB");
    }
    //Loads the JDBC driver.
    private boolean loadJdbcDriver() {
        try {
            System.out.println("Loading JDBC driver...");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            System.out.println("JDBC driver loaded");

            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load JDBC driver!");
            return false;
        }
    }
    //Opens a connection to the database
    private boolean openConnection(String databaseName) {
        try {
            String connectionString =
                    "jdbc:sqlserver://localhost:1433;" +
                            "instanceName=SQLEXPRESS;" +
                            "databaseName=" + databaseName + ";" +
                            "integratedSecurity=true;" +
                            "trustServerCertificate=true;";

            System.out.println(connectionString);

            System.out.println("Connecting to the database...");

            connection = DriverManager.getConnection(connectionString);

            System.out.println("Connected to the database");

            return true;
        } catch (SQLException e) {

            System.out.println("Could not connect to database: " + databaseName);
            System.out.println("Could not connect to the database: " + databaseName);

            System.out.println(e.getMessage());
            return false;
        }
    }

    public void userRegistration(User user) {

        String sql = "INSERT INTO Users (username, hashedPassword, email, firstname, lastname) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstname());
            statement.setString(5, user.getLastname());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
}
