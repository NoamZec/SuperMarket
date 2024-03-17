package com.example.supermarket;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private final Activity context;
    private final String[] title;
    private final String[] des;
    private final ArrayList<byte[]>image;
    private final double[] price;
    private ArrayList<ProductSec> products;

    public MyAdapter(@NonNull Context context,String[] title,String[] des,ArrayList<byte[]> image,double[] price, ArrayList<ProductSec> products) {//constructor of the adapter
        super();
        this.context = (Activity) context;
        this.title = title;
        this.des = des;
        this.image = image;
        this.price = price;
        this.products = products;
    }
    @Override
    public int getCount() {
        return this.products.size();
    }

    @Override
    public Object getItem(int i) {
        return this.products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.products.get(i).getProductId();
    }

    public View getView(int position, View view, ViewGroup parent){//connect the images and make photo to Bitmap
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null,true);
        TextView titleText = rowView.findViewById(R.id.title);
        TextView description = rowView.findViewById(R.id.subtitle);
        TextView price1 = rowView.findViewById(R.id.price);
        ImageView img1 = rowView.findViewById(R.id.icon);
        titleText.setText(title[position]);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image.get(position), 0, image.get(position).length);
        img1.setImageBitmap(bitmap);
        description.setText(des[position]);
        price1.setText(price[position] + "");
        return rowView;
    }
}
