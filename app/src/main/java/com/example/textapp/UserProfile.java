package com.example.textapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    TextView u;
    TextView e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Una");
        String email = intent.getStringExtra("ema");
        u = (TextView) findViewById(R.id.ULable);
        e = (TextView) findViewById(R.id.UEmails);
        u.setText("UserName :" + name);
        e.setText("Email :" + email);
    }
}