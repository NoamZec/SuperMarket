package com.example.supermarket.ui.admin;

import static android.app.Activity.RESULT_CANCELED;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.supermarket.R;
import com.example.supermarket.databinding.FragmentAdminBinding;

public class Admin extends Fragment {
    private ImageView img;
    private FragmentAdminBinding binding;
    private static final int RESULT_OK = 1;
    private AdminViewModel mViewModel;
    private ImageButton camera;
    public static Admin newInstance() {
        return new Admin();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =  FragmentAdminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        camera = root.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        // TODO: Use the ViewModel
    }
    private void openCamera() {//function that opens the camera
        if(askForCameraPermission()) {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera, 1);
        }
    }

    private boolean askForCameraPermission() {//ask for the camera's permission
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA}, 101);
            return ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
}