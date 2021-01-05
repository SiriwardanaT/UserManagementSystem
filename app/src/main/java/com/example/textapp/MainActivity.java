package com.example.textapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

     EditText username;
     EditText Email;
     EditText pass;

     Button save;
     Button update;
     Button delete;
     Button view;
     Customer customer;
     DatabaseReference ref;
     FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.fire_uname);
        pass = (EditText)findViewById(R.id.pass_log);
        Email = (EditText)findViewById(R.id.editTextTextPersonName6);

        save = (Button) findViewById(R.id.button9);
        update = (Button) findViewById(R.id.button10);
        delete = (Button) findViewById(R.id.button11);
        view = (Button) findViewById(R.id.button12);
        auth = FirebaseAuth.getInstance();

        customer = new Customer();

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String G_user = username.getText().toString().trim();
                String G_email = Email.getText().toString().trim();
                String G_pass = pass.getText().toString().trim();
                //validation
                if (G_user.isEmpty()) {
                    username.setError("Please Enter UserName");
                    username.requestFocus();
                    return;
                }
                if (G_email.isEmpty()) {
                    Email.setError("Please Enter Email");
                    Email.requestFocus();
                    return;
                }
                if (G_pass.isEmpty()) {
                    pass.setError("Please Enter Email");
                    pass.requestFocus();
                    return;
                } else {

                    auth.createUserWithEmailAndPassword(Email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String Id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                ref = FirebaseDatabase.getInstance().getReference("Customer").child(Id);

                                customer.setUserName(username.getText().toString().trim());
                                customer.setEmail(Email.getText().toString().trim());
                                customer.setPassword(pass.getText().toString().trim());
                                ref.setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MainActivity.this, "User register successfully!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Invalid datasaved!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(MainActivity.this, "something went wrong !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });



    }



}

