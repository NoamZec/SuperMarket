package com.example.supermarket;

import static com.example.supermarket.ProducrFragment.ARG_PARAM1;
import static com.example.supermarket.ProducrFragment.ARG_PARAM2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

public class PicFragment extends Fragment {

    private PicViewModel mViewModel;
    private TextView text;
    private String mParam1;
    private String mParam2;

    public static PicFragment newInstance(String param1, String param2,String subtitle) {
        PicFragment fragment = new PicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString("subtitle", subtitle); // Set the subtitle parameter
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static void setArgument(Bundle bundle) {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_pic, container, false);
        text =root.findViewById(R.id.text);
        Bundle bundle = this.getArguments();
        String subtitle = bundle.getString("subtitle");
        text.setText(subtitle);
        return root;
    }

}