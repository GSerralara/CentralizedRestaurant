DROP DATABASE IF EXISTS Restaurant;
CREATE DATABASE Restaurant;
Use Restaurant;

DROP TABLE IF EXISTS MenuContainsDish CASCADE;
DROP TABLE IF EXISTS CustomerBookTable CASCADE;
DROP TABLE IF ExISTS TableOrderDish CASCADE;
DROP TABLE IF EXISTS Tables CASCADE;
DROP TABLE IF EXISTS Menu CASCADE;
DROP TABLE IF EXISTS Restaurant CASCADE;
DROP TABLE IF EXISTS Dish CASCADE;
DROP TABLE IF EXISTS Customer CASCADE;

CREATE TABLE Restaurant(
	email VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (username, email)
);

CREATE TABLE Menu(
	id_menu INT AUTO_increment,
    name VARCHAR(255),
    usernameRestaurant VARCHAR(255),
    emailRestaurant VARCHAR(255),
    PRIMARY KEY (id_menu),
    FOREIGN KEY (usernameRestaurant, emailRestaurant) REFERENCES Restaurant(username, email)
);

CREATE TABLE Dish(
	id_dish INT AUTO_increment,
    price FLOAT,
    name VARCHAR(255),
    units INT,
    cooking_time TIME,
    sold_out BOOL,
    PRIMARY KEY (id_dish)
);

CREATE TABLE MenuContainsDish(
	id_menu INT,
    id_dish INT,
    PRIMARY KEY (id_menu, id_dish),
    FOREIGN KEY (id_menu) REFERENCES Menu(id_menu),
    FOREIGN KEY (id_dish) REFERENCES Dish(id_dish)
);

CREATE TABLE tables(
	id_table INT AUTO_increment,
    usernameRestaurant VARCHAR(255),
    emailRestaurant VARCHAR(255),
    number_client INT,
    PRIMARY KEY (id_table),
    FOREIGN KEY (usernameRestaurant, emailRestaurant) REFERENCES Restaurant(username, email)
);

CREATE TABLE Customer(
	username VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (username)
);

CREATE TABLE CustomerBookTable(
	usernameCustomer VARCHAR(255),
    id_table INT,
    date DATETIME,
    name VARCHAR(255),
    PRIMARY KEY (usernameCustomer, id_table),
    FOREIGN KEY (usernameCustomer) REFERENCES Customer(username),
    FOREIGN KEY (id_table) REFERENCES Tables(id_table)
);

CREATE TABLE TableOrderDish(
	id_table INT,
    id_dish INT,
    quantity INT,
	date DATETIME,
	cooking BOOL,
    served BOOL,
    paid BOOL,
    PRIMARY KEY (id_table, id_dish),
    FOREIGN KEY (id_table) REFERENCES Tables(id_table),
	FOREIGN KEY (id_dish) REFERENCES Dish(id_dish)
);
