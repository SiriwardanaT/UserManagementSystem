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

public class Login extends AppCompatActivity {
    EditText l_email,l_pass;
    Button l_btn;
    FirebaseAuth auth;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        l_email = (EditText)findViewById(R.id.log_name);
        l_pass = (EditText)findViewById(R.id.log_pass);
        l_btn  = (Button)findViewById(R.id.login_btn);
        auth = FirebaseAuth.getInstance();

        login();

    }

    private void login() {
        l_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = l_email.getText().toString().trim();
                String password = l_pass.getText().toString().trim();

                if(email.isEmpty()){
                    l_email.setError("please enter username");
                    l_email.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    l_pass.setError("please enter password");
                    l_pass.requestFocus();
                    return;
                }
                else{
                     auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                Intent intent = new Intent(Login.this,UserProfile.class);
                                startActivity(intent);
                             }
                             else{
                                 Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });

                }

            }
        });








    }


}