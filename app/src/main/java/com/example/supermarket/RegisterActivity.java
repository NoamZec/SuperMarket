package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText password,email;
    private FireBase fb;
    private Button register;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiser);
        fb = new FireBase(FirebaseAuth.getInstance(), this);
        register = findViewById(R.id.LogIn2);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email13);
        password = findViewById(R.id.password8);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fb.register(email.getText().toString(), password.getText().toString());
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }

}