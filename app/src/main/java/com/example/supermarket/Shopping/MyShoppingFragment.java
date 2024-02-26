package com.example.supermarket.Shopping;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.supermarket.R;

public class MyShoppingFragment extends Fragment {

    private MyShoppingViewModel mViewModel;
    private Button sendEmail;

    public static MyShoppingFragment newInstance() {
        return new MyShoppingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root  =  inflater.inflate(R.layout.fragment_my_shopping, container, false);
        sendEmail = root.findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//check the function is correct
                sendEmail();
            }
        });
        return root;
    }
    private void sendEmail(){
        Intent sendEmail = new Intent(Intent.ACTION_SEND);
        sendEmail.setData(Uri.parse("mailto:"));
        sendEmail.setType("text/plain");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyShoppingViewModel.class);
        // TODO: Use the ViewModel
    }

}