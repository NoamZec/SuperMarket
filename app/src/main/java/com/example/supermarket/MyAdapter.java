package com.example.supermarket;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;
    private final String[] des;
    private final ArrayList<byte[]>image;
    private final double[] price;

    public MyAdapter(@NonNull Context context,String[] title,String[]des,ArrayList<byte[]>image,double[]price) {//constructor of the adapter
        super(context, R.layout.mylist, title);
        this.context = (Activity) context;
        this.title = title;
        this.des = des;
        this.image = image;
        this.price = price;
    }
    public View getView(int position, View view, ViewGroup parent){//connect the images and make photo to Bitmap
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        TextView titleText =(TextView) rowView.findViewById(R.id.title);
        TextView description =(TextView) rowView.findViewById(R.id.subtitle);
        TextView price1 = (TextView) rowView.findViewById(R.id.price);
        ImageView img1 = (ImageView) rowView.findViewById(R.id.icon);
        titleText.setText(title[position]);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image.get(position), 0, image.get(position).length);
        img1.setImageBitmap(bitmap);
        description.setText(des[position]);
        price1.setText(price[position] + "");
        return rowView;
    }
}
