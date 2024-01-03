package com.example.supermarket.ui.admin;

import static android.app.Activity.RESULT_CANCELED;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.CaseMap;
import android.icu.util.ULocale;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.supermarket.FireBase;
import com.example.supermarket.ProductSec;
import com.example.supermarket.R;
import com.example.supermarket.databinding.FragmentAdminBinding;
import com.example.supermarket.ui.upload.Upload;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.Locale;

public class Admin extends Fragment {
    private FragmentAdminBinding binding;
    private static final int RESULT_OK = 1;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private AdminViewModel mViewModel;
    private EditText title, subtitle, price;
    private Button btn;
    private ImageView img;
    private ImageButton camera_admin;
    private  Bitmap imageBitmap;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private Spinner spinner;
    private FirebaseStorage storage;

    public static Admin newInstance() {
        return new Admin();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin, container, false);
        btn = root.findViewById(R.id.btn);
        title = root.findViewById(R.id.title);
        subtitle = root.findViewById(R.id.editText);
        price = root.findViewById(R.id.price);
        spinner = root.findViewById(R.id.spinner);

        storage = FirebaseStorage.getInstance();

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(arrayAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageBitmap != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    ProductSec productSec = new ProductSec(title.getText().toString(),subtitle.getText().toString(), spinner.getSelectedItem().toString(),Double.valueOf(price.getText().toString()));
                    byte[] data = byteArrayOutputStream.toByteArray();
                    // [START upload_create_reference]
                    // Create a storage reference from our app
                    StorageReference storageRef = storage.getReference();

                    // Create a reference to "mountains.jpg"
                    StorageReference productRef = storageRef.child("images/" + title.getText() + ".jpg");

                    UploadTask uploadTask = productRef.putBytes(data);
                    databaseReference.child("ProductSec").child(productSec.getTitle()).setValue(productSec).addOnCompleteListener(new OnCompleteListener<Void>() {
                        //there is a problem with this line...
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("SUCCESS", "Image Uploaded");
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("ERROR", e.getMessage());
                                    }
                                });
                            }
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "You need to take a picture first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
               if (o.getResultCode() == getActivity().RESULT_OK) {
                    Intent data = o.getData();
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            imageBitmap = (Bitmap) extras.get("data");
                            img = root.findViewById(R.id.eye);
                            img.setVisibility(View.VISIBLE);
                            img.setImageBitmap(imageBitmap);
                           ByteArrayOutputStream stream = new ByteArrayOutputStream();
                           imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] bytes = stream.toByteArray();
                        }
                   }
                }

            }
        });

        camera_admin = root.findViewById(R.id.camera_admin);
        camera_admin.setOnClickListener(view -> {//open the camera when you click

            openCamera();
        });

        return root;
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