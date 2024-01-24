package com.example.supermarket.products.food;

public class Vegetables extends Food {
    private String countryImport;
    public Vegetables(String title, String subtitle, String category, double price, double weight, String countryImport) {
        super(title, subtitle, category, price, weight);
        this.countryImport = countryImport;
    }
}
