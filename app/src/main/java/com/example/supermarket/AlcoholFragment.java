package com.example.supermarket;

import static android.app.Activity.RESULT_OK;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlcoholFragment extends Fragment {
    private FireBase fireBase;
    private ListView list;
    private ArrayList<ProductSec> products;
    private double[] price;
    private ArrayList<byte[]> photos;
    private String[] description;
    private String[] title;
    private LoadingDialogBar loadingDialogBar;
    public static AlcoholFragment newInstance() {
        AlcoholFragment fragment = new AlcoholFragment();
        return fragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alcohol, container, false);
        list = root.findViewById(R.id.list);
        loadingDialogBar = new LoadingDialogBar(getContext());
        loadingDialogBar.ShowDialog("Loading...");
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
                    photos.add(fireBase.getPic(products.get(i)));
                }
                MyAdapter adapter = new MyAdapter(getContext(), title, description, photos, price, products);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // TODO: pass information from one fragment to another
                        PicFragment picFragment = new PicFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("title", title[i]);
                        bundle.putString("description", description[i]);
                        bundle.putByteArray("photos", photos.get(i));
                        bundle.putDouble("price", price[i]);
                        bundle.putString("category", products.get(i).getCategory());
                        bundle.putInt("id", products.get(i).getProductId());
                    }
                });
                loadingDialogBar.HideDialog();
            }
        });
        return root;
    }
}