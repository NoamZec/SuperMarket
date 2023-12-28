package com.example.supermarket;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageButton;

public class ProductSec {
    private byte[] ImageBitmap;
    private String title, subtitle, category;
    private double price;

    public ProductSec(byte[] imageBitmap, String title, String subtitle, String category, double price) {
        ImageBitmap = imageBitmap;
        this.title = title;
        this.subtitle = subtitle;
        this.category = category;
        this.price = price;
    }

    public byte[] getImageBitmap() {
        return ImageBitmap;
    }

    public void setImageBitmap(byte[] imageBitmap) {
        ImageBitmap = imageBitmap;
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
}
