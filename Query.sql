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

    PRIMARY KEY (userID)
);