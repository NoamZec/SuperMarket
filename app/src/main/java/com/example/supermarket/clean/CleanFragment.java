package com.example.supermarket.clean;

import android.content.ClipData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.supermarket.FireBase;
import com.example.supermarket.MyAdapter;
import com.example.supermarket.ProductSec;
import com.example.supermarket.R;

import java.util.ArrayList;

public class CleanFragment extends Fragment {

    private FireBase fireBase;
    private ListView list;
    private ArrayList<ProductSec> products;
    private double[] price;
    private ArrayList<byte[]> photos;
    private String[] description;
    private String[] title;

    public static CleanFragment newInstance(String param1, String param2) {
        CleanFragment fragment = new CleanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_alcohol, container, false);

        list = root.findViewById(R.id.list);
        fireBase = new FireBase(getContext());

        fireBase.getInformation("Clean",value -> {
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
                    photos.add(fireBase.getPic(products.get(i)));

                }

                MyAdapter adapter = new MyAdapter(getContext(), title, description, photos, price, products);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // TODO: go to fragment and show information

                    }
                });

            }
        });

        return root;
    }
}