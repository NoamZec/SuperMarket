package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class FireBase {//constructor
    private final FirebaseAuth auth;
    private Context context; // toast, move from one activity to another
    private ArrayList<ProductSec> allProducts, products;
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private DatabaseReference mDatabase;

    public FireBase(Context context){
        this.auth = FirebaseAuth.getInstance();
        this.storage = FirebaseStorage.getInstance();
        this.storageRef = storage.getReference();
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.context = context;
        this.allProducts = new ArrayList<>();
        this.products = new ArrayList<>();
    }
    public void signIn(String email, String password){
        Task<AuthResult> authResultTask = auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {//move to Home Activity
                if(task.isSuccessful()){
                    Intent i = new Intent(context.getApplicationContext(), HomeActivity.class);
                    context.startActivity(i);
                    Toast.makeText(context, "sign in successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context, "sign in failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage().toString().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(String email, String password) {
        if(!email.isEmpty() && !password.isEmpty()) {
             if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(context, "E-mail is not valid ", Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.length() < 6){
                Toast.makeText(context, "password needs to be 6 chars at least", Toast.LENGTH_SHORT).show();
                return;
            }
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {//create the register site
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {//move to Home
                    Intent intent = new Intent(context.getApplicationContext(), HomeActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context, "Register Successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            Toast.makeText(context, "One of the fields is empty", Toast.LENGTH_LONG).show();
        }

    }

    public void getInformation(String category, Listener<ArrayList<ProductSec>> listener) {
        // [START post_value_event_listener]
   //     ValueEventListener productListener = new ValueEventListener() {
       //     @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
////                ProductSec product = dataSnapshot.getValue(ProductSec.class);
////                if (product.getCategory().equals(category)) {
////
////                }
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    ProductSec product = snapshot.getValue(ProductSec.class);
//                    allProducts.add(product);
//                }
//
//                for (ProductSec productSec : allProducts) {
//                    if (productSec.getCategory().equals(category)) {
//                        products.add(productSec);
//                    }
//                }
//
//
//                listener.onListen(products);
//            }


//          //  @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//            }
//        };
//        databaseReference.addValueEventListener(productListener);
//        // [END post_value_event_listener]
        mDatabase.child("products").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                try {
                    listener.onListen(getProducts(dataSnapshot, category));
                } catch (InterruptedException e) {
                    Log.e("ERROR", e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        });
    }

    private ArrayList<ProductSec> getProducts(DataSnapshot dataSnapshot, String category) {
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            ProductSec product = snapshot.getValue(ProductSec.class);
            allProducts.add(product);
        }

        for (ProductSec productSec : allProducts) {
            if (productSec.getCategory().equals(category)) {
                products.add(productSec);
            }
        }

        return products;
    }

    public void uploadPic(String path, ProductSec productSec, byte[] data) {
        // Create a reference to "mountains.jpg"
        StorageReference productRef = storageRef.child("images/" + productSec.getTitle() + ".jpg");

        UploadTask uploadTask = productRef.putBytes(data);
        databaseReference.child("products").child(path).setValue(productSec).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    }

//    public void getPicture(ProductSec product, Listener<byte[]> listener) {
//
//        final long ONE_MEGABYTE = 5 * (1024 * 1024);
//        storageRef.child("images").child(product.getTitle() + ".jpg").getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                try {
//                    listener.onListen(bytes);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(context, exception.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                Log.e("ERROR", exception.getMessage());
//            }
//        });
//
//    }
    public byte[] getPic(ProductSec product) {
        Task<byte[]> task = storageRef.child("images").child(product.getTitle() + ".jpg").getBytes(1024*1024);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!task.isComplete()) {
                    Log.i("Wait", "Please Wait");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            Toast.makeText(context, "Waiting for thread failed", Toast.LENGTH_SHORT).show();
        }

        byte[] bytes = task.getResult();
        return bytes;
    }

//    public byte[] getPicture(ProductSec product) throws InterruptedException {
//        final List<byte[]> bytesContainer = new ArrayList<>();
//        final long ONE_MEGABYTE = 5 * (1024 * 1024);
//        storageRef.child("images").child(product.getTitle() + ".jpg").getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                bytesContainer.add(bytes);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(context, exception.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                Log.e("ERROR", exception.getMessage());
//            }
//        });
//
//        while (bytesContainer.isEmpty()) {
//            Thread.sleep(50);
//            Log.i("INFO", "SLEEP");
//        }
//
//        return bytesContainer.get(0);
//    }

    public static void findProductId(int id, Listener<Boolean> listener){
        databaseReference.child("products").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    for(DataSnapshot snapshot : task.getResult().getChildren()){
                        if(snapshot.child(id + "").exists()){
                            try {
                                listener.onListen(true);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            try {
                                listener.onListen(false);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        });
    }

    public FirebaseAuth getAuth(){return  auth;}
}
