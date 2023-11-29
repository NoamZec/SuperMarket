package com.example.supermarket.ui.alcohol;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.supermarket.R;
import com.example.supermarket.databinding.FragmentAlcoholBinding;

public class AlcoholFragment extends Fragment {
    private AlcoholViewModel mViewModel;
    private FragmentAlcoholBinding binding;
    private ListView list;

    public static AlcoholFragment newInstance() {
        return new AlcoholFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentAlcoholBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        root.findViewById(R.id.list);
        return inflater.inflate(R.layout.fragment_alcohol, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AlcoholViewModel.class);
        // TODO: Use the ViewModel
    }
}