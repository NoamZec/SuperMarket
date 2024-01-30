package com.example.supermarket;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageButton;

public class ProductSec {
    private String title, subtitle, category;
    private double price;
  //  private String productId;

    public ProductSec(String title, String subtitle, String category, double price) {
        this.title = title;
        this.subtitle = subtitle;
        this.category = category;
        this.price = price;
     //   this.productId = productId;
    }
  // public String getProductId{return productId;}

    public String getTitle() {
        return title;
    }
  //  public void SetProductId(String productId){this.productId = productId;}
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
