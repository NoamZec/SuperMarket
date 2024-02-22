package com.example.supermarket;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageButton;

import java.util.Random;

public class ProductSec {
    private String title, subtitle, category;
    private double price;
    private final int productId;

    public ProductSec(String title, String subtitle, String category, double price) {
        this.title = title;
        this.subtitle = subtitle;
        this.category = category;
        this.price = price;
        this.productId = generateId();
    }

    public ProductSec() {
        this.productId = generateId();
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public  int getProductId(){return productId;}
    private boolean foundId = false;
    private int generateId() {
        Random rnd = new Random();
        int id = 100000000 + rnd.nextInt(900000000);
        FireBase.findProductId(id, temp -> foundId = temp);
        if(foundId){
            return generateId();
        }
        return id;
    }
}
