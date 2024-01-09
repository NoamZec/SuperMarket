package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class FireBase {//constructor
    private final FirebaseAuth auth;
    private Context context; // toast, move from one activity to another
    public FireBase(FirebaseAuth auth, Context context){
        this.auth = auth;
        this.context = context;
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

    public void getData(String category, ArrayList<ProductSec> products) {//get the Data
        ArrayList<byte[]> images = new ArrayList<>();
        for(int i = 0;i < products.size();i++){
            if(products.get(i).getCategory() == category){
                //TODO: PRODACT
            }
        }
    }
    //TODO: know what Lambada is
    public void getPic(){

    }
    public FirebaseAuth getAuth(){return  auth;}
}
