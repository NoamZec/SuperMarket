package com.example.supermarket;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AlcoholFragment extends Fragment {

    private AlcoholViewModel2 mViewModel;
    private FireBase fireBase;
    private LinearLayout linearLayout;
    public static AlcoholFragment newInstance() {
        return new AlcoholFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alcohol, container, false);
        linearLayout = root.findViewById(R.id.linearLayout);
        fireBase.addPostEventListener("Alcohol", value -> {
            if (value instanceof ArrayList<?>) {//check if the value is kind of arrayList
                ArrayList<ProductSec> products = (ArrayList<ProductSec>) value;
                for (int i = 0; i < products.size(); i++) {
                    //TODO: THINK HOW TO DO 3 LINEARLAYOUT AFTER EVEY
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