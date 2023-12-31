package com.example.supermarket.ui.upload;

import static android.app.Activity.RESULT_CANCELED;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.example.supermarket.ui.admin.AdminViewModel;

import java.io.ByteArrayOutputStream;

public class Upload extends Fragment {

    private FragmentAdminBinding binding;
    private static final int RESULT_OK = 1;
    private ImageButton camera_admin;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private ImageView img;
    private UploadViewModel mViewModel;

    public static Upload newInstance() {
        return new Upload();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin, container, false);

//        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult o) {
//                if (o.getResultCode() == getActivity().RESULT_OK) {
//                    Intent data = o.getData();
//                    if (data != null) {
//                        Bundle extras = data.getExtras();
//                        if (extras != null) {
//                            Bitmap imageBitmap = (Bitmap) extras.get("data");
//                            img = root.findViewById(R.id.eye);
//                            img.setVisibility(View.VISIBLE);
//                            img.setImageBitmap(imageBitmap);
//                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                            byte[] bytes = stream.toByteArray();
//                        }
//                    }
//                }
//            }
//        });
//
//        camera_admin = root.findViewById(R.id.camera_admin);
//        camera_admin.setOnClickListener(view -> {//open the camera when you click
//            openCamera();
//        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UploadViewModel.class);
        // TODO: Use the ViewModel
    }

    private void openCamera() {//function that opens the camera
        if (askForCameraPermission()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                cameraLauncher.launch(intent);
            }
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
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

}