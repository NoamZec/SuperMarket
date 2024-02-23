package com.example.supermarket;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class AlcoholFragment extends Fragment {

    private AlcoholViewModel2 mViewModel;
    private FireBase fireBase;
    private ArrayAdapter arrayAdapter;
    private ListView list;
    private ArrayList<ProductSec> products;
    private double[] price;
    private ArrayList<byte[]> photos;
    private String[] description;
    private String[] title;
    public static AlcoholFragment newInstance() {
        return new AlcoholFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alcohol, container, false);
        list = root.findViewById(R.id.list);
        fireBase = new FireBase(getContext());

        fireBase.getInformation("Alcohol",value -> {
            if (value instanceof ArrayList<?>){
                products = (ArrayList<ProductSec>) value;
                price = new double[products.size()];
                photos = new ArrayList<>();
                description = new String[products.size()];
                title = new String[products.size()];
                for(int i = 0;i < products.size();i++){
                    price[i]  = products.get(i).getPrice();
                    price[i]  = products.get(i).getPrice();
                    title[i] = products.get(i).getTitle();
                    description[i] = products.get(i).getSubtitle();
                    fireBase.getPicture(products.get(i), bytes -> photos.add(bytes));
//                    photos.add(fireBase.getPicture(products.get(i)));

                }

                while (photos.isEmpty()) {
                    Thread.sleep(50);
                    Log.i("INFO", "SLEEP");
                }

                MyAdapter adapter = new MyAdapter(getContext(), title, description, photos, price);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
            }
        });
        return inflater.inflate(R.layout.fragment_alcohol, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AlcoholViewModel2.class);
        // TODO: Use the ViewModel
    }

}