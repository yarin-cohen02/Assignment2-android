package com.example.assignment2.Classes;

public class Item {
    private String name;
    private int price;
    private int quantity; // New attribute for quantity

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        this.quantity = 0; // Default quantity is 0
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    // Getter method for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Setter method for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
