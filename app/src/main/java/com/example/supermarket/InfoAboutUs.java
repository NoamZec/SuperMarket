package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class InfoAboutUs extends AppCompatActivity {

    private IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private InternetConnectionReciever internetConnectionReciever;
    private Button skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_about_us);
        internetConnectionReciever = new InternetConnectionReciever();
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoAboutUs.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onStart() {//start the receiver
        super.onStart();
        registerReceiver(internetConnectionReciever, filter);
    }
}