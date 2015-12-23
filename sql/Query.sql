CREATE DATABASE IF NOT EXISTS webstore;

USE webstore;

CREATE TABLE Product (
ProductId varchar(10) NOT NULL,
ProductName varchar(50) NOT NULL,
UnitPrice double(10,2) NOT NULL,
Description varchar(200),
Manufacturer varchar(30),
Category varchar(20) NOT NULL,
UnitsInStock int(10),
UnitsInOrder int(5),
ProductImage mediumblob,
ProductManual mediumblob,
PRIMARY KEY (ProductId)
);