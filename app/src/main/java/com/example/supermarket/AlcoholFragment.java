package com.example.supermarket;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AlcoholFragment extends Fragment {

    private AlcoholViewModel2 mViewModel;
    FireBase fireBase;

    public static AlcoholFragment newInstance() {
        return new AlcoholFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fireBase.addPostEventListener("Alcohol", ProductSec->{
            //TODO: the array list of the products in alcohol fragment
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