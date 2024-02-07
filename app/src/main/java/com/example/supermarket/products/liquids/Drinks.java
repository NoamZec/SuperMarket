package com.example.supermarket.products.liquids;

import com.example.supermarket.ProductSec;

public class Drinks extends ProductSec {
    private double liter;
    public Drinks(String title, String subtitle, String category, double price, double liter) {
        super(title, subtitle, category, price);
        this.liter = liter;
    }
    public  double getLiter(){return this.liter;}
}
