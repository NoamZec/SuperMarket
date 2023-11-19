package com.example.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class SignInActivity extends AppCompatActivity {

    private EditText password,email;
    private Button register2;
    private FireBase fb;
    private  Button login;
    private Button fingerPrint;
    private BiometricPrompt promptInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        register2 = findViewById(R.id.register2);
        fb = new FireBase(FirebaseAuth.getInstance(), this);
        login = findViewById(R.id.LogIn2);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password4);
        fingerPrint = findViewById(R.id.fingerPrint);

        fingerPrint.setOnClickListener(v -> {//start the finger print
            BiometricPrompt.PromptInfo promptInfo =
                    new BiometricPrompt.PromptInfo.Builder()
                            .setTitle("Biometric login for my app")
                            .setSubtitle("Log in using your biometric credential")
                            .setNegativeButtonText("Cancel")
                            .build();
            BiometricPrompt biometricPrompt1 = AfterFingerPrint();
            biometricPrompt1.authenticate(promptInfo);
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fb.signIn(email.getText().toString(), password.getText().toString());
            }
        });
        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    public BiometricPrompt AfterFingerPrint() {//After fingerPrint
        Executor executor = ContextCompat.getMainExecutor(SignInActivity.this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(SignInActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Login succeed ", Toast.LENGTH_SHORT).show();//toast if login succeed
                Intent i = new Intent(SignInActivity.this,HomeActivity.class);
                startActivity(i);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        return biometricPrompt;
    }
}