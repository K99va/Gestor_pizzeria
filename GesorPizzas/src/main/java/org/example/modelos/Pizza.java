package org.example.modelos;

import java.util.List;

public class Pizza {
    private final String type;
    private final double price;
    private final List<String> toppings;

    public Pizza(String type, double price, List<String> toppings) {
        this.type = type;
        this.price = price;
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    // Getters...
}