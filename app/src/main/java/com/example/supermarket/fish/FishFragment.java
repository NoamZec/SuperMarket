package com.example.supermarket.fish;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class FishFragment extends Fragment {

    private FireBase fireBase;
    private ListView list;
    private ArrayList<ProductSec> products;
    private double[] price;
    private ArrayList<byte[]> photos;
    private String[] description;
    private String[] title;

    public static FishFragment newInstance(String param1, String param2) {
        FishFragment fragment = new FishFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_fish, container, false);
        list = root.findViewById(R.id.list);
        fireBase = new FireBase(getContext());
        return root;
    }
}