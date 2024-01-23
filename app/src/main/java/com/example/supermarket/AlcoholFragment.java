package com.example.supermarket;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public static AlcoholFragment newInstance() {
        return new AlcoholFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alcohol, container, false);
        list = root.findViewById(R.id.list);
     /*   fireBase.addPostEventListener("Alcohol", value -> {
            if (value instanceof ArrayList<?>) {//check if the value is kind of arrayList
                ArrayList<ProductSec> products = (ArrayList<ProductSec>) value;
                int size = 0;
                if (products.size() % 3 == 0) {
                    size = products.size()/3;
                } else {
                    size = (products.size() / 3) + 1;
                }
                for (int i = 0; i < products.size(); i++) {

                }
            }
        });

      */
        fireBase.addPostEventListener("Alcohol",value -> {
            if (value instanceof ArrayList<?>){
                ArrayList<ProductSec> products = (ArrayList<ProductSec>) value;
                double[]price = new double[products.size()];
                byte[]photos = new byte[products.size()];
                String[]description = new String[products.size()];
                String[]title = new String[products.size()];
                for(int i = 0;i < products.size();i++){
                    price[i]  = products.get(i).getPrice();
                    //  photos[i]  = products.get(i).;
                    price[i]  = products.get(i).getPrice();
                }
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