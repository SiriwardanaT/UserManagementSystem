package com.example.textapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    TextView u;
    TextView p;
    FirebaseAuth auth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        u = (TextView)findViewById(R.id.ULable);
        p =(TextView)findViewById(R.id.UEmails);
        auth= FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference("Customer");

        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Customer cusp = snapshot.getValue(Customer.class);

                    if(cusp != null){
                        u.setText(cusp.getUserName());
                        p.setText(cusp.getEmail());
                    }
                    else{
                        Toast.makeText(UserProfile.this, "check another one", Toast.LENGTH_SHORT).show();
                    }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
               // Toast.makeText(UserProfile.this, "No details ", Toast.LENGTH_SHORT).show();
            }
        });






    }
}