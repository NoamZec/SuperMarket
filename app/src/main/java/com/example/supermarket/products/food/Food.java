package com.example.supermarket.products.food;

import com.example.supermarket.ProductSec;

public class Food extends ProductSec {
    private double weight;
    public Food(String title, String subtitle, String category, double price, double weight) {
        super(title, subtitle, category, price);
        this.weight = weight;
    }
    public double getWeight(){return this.weight;}
}