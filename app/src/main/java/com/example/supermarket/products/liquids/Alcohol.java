package com.example.supermarket.products.liquids;

public class Alcohol extends Drinks {
    private int alcoholPercent;
    public Alcohol(String title, String subtitle, String category, double price, double liter, int alcoholPercent) {
        super(title, subtitle, category, price, liter);
        this.alcoholPercent = alcoholPercent;
    }
    public int getAlcoholPercent(){return this.alcoholPercent;}
}