package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBase {//constructor
    private FirebaseAuth auth;
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
    public void register(String email, String password){
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
    public FirebaseAuth getAuth(){return  auth;}

    public void setAuth(FirebaseAuth auth){this.auth = auth;}
}
