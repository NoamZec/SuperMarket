package com.example.supermarket.clean;

import static com.example.supermarket.ProducrFragment.ARG_PARAM1;
import static com.example.supermarket.ProducrFragment.ARG_PARAM2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.supermarket.FireBase;
import com.example.supermarket.LoadingDialogBar;
import com.example.supermarket.MyAdapter;
import com.example.supermarket.PicFragment;
import com.example.supermarket.ProductSec;
import com.example.supermarket.R;

import java.util.ArrayList;

public class CleanFragment extends Fragment {

    private FireBase fireBase;
    private ListView list1;
    private ArrayList<ProductSec> products;
    private double[] price;
    private ArrayList<byte[]> photos;
    private String[] description;
    private String[] title;
    private LoadingDialogBar loadingDialogBar;

    public static CleanFragment newInstance(String param1, String param2) {
        CleanFragment fragment = new CleanFragment();
        return fragment;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_clean, container, false);
        list1 = root.findViewById(R.id.list1);
        loadingDialogBar = new LoadingDialogBar(getContext());
        loadingDialogBar.ShowDialog("Loading...");
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
                list1.setAdapter(adapter);
                list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // TODO: go to fragment and show information in addition I need to know how to pass everything...
                        String subtitle = products.get(i).getSubtitle();
                        double price= products.get(i).getPrice();
                        Bundle bundle = new Bundle();
                        bundle.putString("subtitle",subtitle);
                        bundle.putDouble("price",price);
                        PicFragment fragment = PicFragment.newInstance("param1","param2",subtitle);
                        fragment.setArgument(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.app_bar_home,fragment).commit();
                    }
                });
                loadingDialogBar.HideDialog();

            }
        });

        return root;
    }

}