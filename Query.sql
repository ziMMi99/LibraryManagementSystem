USE master;

if DB_ID('MyPortfolioDB') IS NOT NULL
  DROP DATABASE MyPortfolioDB;

CREATE DATABASE MyPortfolioDB;
GO

USE MyPortfolioDB;

CREATE TABLE Users (
    userID int IDENTITY(1,1) NOT NULL,
    username NVARCHAR(50) NOT NULL,
    hashedPassword NVARCHAR(128) NOT NULL,
    email NVARCHAR(50) NOT NULL,
    firstname NVARCHAR(50) NOT NULL,
    lastname NVARCHAR(50) NOT NULL,
    admin TINYINT NOT NULL,

    PRIMARY KEY (userID)
);

CREATE TABLE borrowedBooks (
    userID int NOT NULL FOREIGN KEY REFERENCES Users(userID) ON DELETE NO ACTION,
    bookID int NOT NULL FOREIGN KEY REFERENCES Book(bookID) ON DELETE NO ACTION,

    CONSTRAINT composite_key PRIMARY KEY (userID, bookID)
);

CREATE TABLE Book (
    bookID int IDENTITY(1,1) NOT NULL,
    title NVARCHAR(100) NOT NULL,
    author NVARCHAR(100) NOT NULL,
    genre NVARCHAR(100) NOT NULL,
    quantity int NOT NULL,
    isAvailable TINYINT NOT NULL,

    PRIMARY KEY (bookID)
);

INSERT INTO Book (title, author, genre, quantity, isAvailable) VALUES
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 2, 1),
('1984', 'George Orwell', 'Dystopian', 2, 1),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Classic', 1, 1),
('Pride and Prejudice', 'Jane Austen', 'Romance', 2, 1),
('The Catcher in the Rye', 'J.D. Salinger', 'Coming-of-age', 1, 1),
('Brave New World', 'Aldous Huxley', 'Science Fiction', 2, 1),
('The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', 2, 1),
('Animal Farm', 'George Orwell', 'Allegory', 1, 1),
('Frankenstein', 'Mary Shelley', 'Gothic', 2, 1),
('Moby-Dick', 'Herman Melville', 'Adventure', 1, 1),
('Jane Eyre', 'Charlotte Bronte', 'Gothic Romance', 2, 1),
('The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 2, 1),
('War and Peace', 'Leo Tolstoy', 'Historical Fiction', 1, 1),
('The Picture of Dorian Gray', 'Oscar Wilde', 'Gothic', 2, 1),
('Wuthering Heights', 'Emily Bronte', 'Gothic Romance', 1, 1),
('Don Quixote', 'Miguel de Cervantes', 'Satire', 2, 1),
('Gone with the Wind', 'Margaret Mitchell', 'Historical Romance', 1, 1),
('The Odyssey', 'Homer', 'Epic', 2, 1),
('Lord of the Flies', 'William Golding', 'Allegory', 1, 1),
('A Tale of Two Cities', 'Charles Dickens', 'Historical Fiction', 2, 1);




