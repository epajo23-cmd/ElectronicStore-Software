# ElectronX - Electronics Store Management System

ElectronX is a desktop application developed in Java and JavaFX for managing an electronics store. It was created as an Object-Oriented Programming project by Eden Pajo(github.com/epajo23-cmd), Thomas Kroj(github.com/tthomas2513), Nadire Lazri, Marin Tartaraj and includes role-based access for administrators, managers, and cashiers.

---

## Features

### Login System
- Role-based authentication
- Separate access for:
  - Administrator
  - Manager
  - Cashier

### Administrator
- Create employees
- Delete employees
- View managers
- View cashiers
- View admins
- View total income
- View total spendings
- View total turnover

### Manager
- View managed sectors
- View managed cashiers
- View sector stock
- View sector turnover
- Restock items

### Cashier
- View available items
- Add items to cart
- Remove items from cart
- Calculate total amount
- Generate bills
- Clear cart

---

## Technologies Used

- Java
- JavaFX
- Object-Oriented Programming
- File-based data storage

---
Data Storage

The project uses local files for storing data such as:

employees
sectors
suppliers
items
bills

Generated bills are saved in the DAO folder as text files.

How to Run

1.Open the project in Eclipse or another Java IDE

2.Make sure Java 21 is installed

3.Add the required JavaFX libraries

4.Run:

src/main/Main.java

Demo Access

Demo users and credentials are included in the demo data.txt file.

Notes
This project was developed for educational purposes
The application is functional and can be extended further
Contributors
Thomas Kroj
Eden Pajo
Marin Tartaraj
Nadire Lazri
