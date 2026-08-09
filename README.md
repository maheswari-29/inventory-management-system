# Inventory Manager (Java Console App)

A simple console-based inventory management system built to practice OOP concepts:
classes, inheritance, polymorphism, custom exceptions, and file handling.

## How to run

1. Open a terminal in this folder.
2. Compile:
   ```
   javac *.java
   ```
3. Run:
   ```
   java InventoryManagerApp
   ```

## Files

- `Item.java` — abstract parent class (id, name, quantity, price)
- `Electronics.java` — child class, adds warranty
- `Grocery.java` — child class, adds expiry date
- `Inventory.java` — manages the list of items, saves/loads data to `inventory_data.txt`
- `InvalidQuantityException.java`, `OutOfStockException.java`, `ItemNotFoundException.java` — custom exceptions
- `InventoryManagerApp.java` — main class, runs the menu

## Features

- Add electronics or grocery items
- Sell items (reduces stock, blocks if not enough stock)
- Restock items
- Remove items
- Display all items
- Data is saved to a file automatically, so it's still there next time you run the program
