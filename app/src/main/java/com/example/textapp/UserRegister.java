package com.example.textapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class UserRegister extends AppCompatActivity {
    //intialize all ids
    EditText R_userName,R_email,R_pass,R_phone;
    Button R_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        R_userName = (EditText) findViewById(R.id.reg_username);
        R_email = (EditText)findViewById(R.id.reg_email);
        R_pass = (EditText) findViewById(R.id.reg_pass);
        R_phone = (EditText)findViewById(R.id.reg_phone);
        R_button = (Button)findViewById(R.id.reg_button);
       // R_login = (TextView) findViewById(R.id.reg_reg_log);
        //validation
        validation();

    }
    public void validation(){

        R_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _G_username = R_userName.getText().toString().trim();
                String _G_email   = R_email.getText().toString().trim();
                String _G_phone  = R_phone.getText().toString().trim();
                String _G_pass = R_pass.getText().toString().trim();

                if(_G_username.isEmpty()){
                    R_userName.setError("Invalid UserName");
                    R_userName.requestFocus();
                    return;
                    //Toast.makeText(UserRegister.this,"invalid",Toast.LENGTH_SHORT).show();
                }
                if(_G_pass.isEmpty()){
                    R_pass.setError("Invalid pass");
                    R_pass.requestFocus();
                    return;
                }
                if(_G_pass.length() <= 6){
                    R_pass.setError("Invalid pass");
                    R_pass.requestFocus();
                    return;
                }
                if(!(_G_phone.length() == 10)){
                    R_phone.setError("Invalid phoneNumber");
                    R_phone.requestFocus();
                    return;
                }
                if(_G_email.isEmpty()){
                    R_email.setError("Invalid Email");
                    R_email.requestFocus();
                    return;
                }
                if(!(_G_email.contains("@"))){
                    R_email.setError("Missing @ ");
                    R_email.requestFocus();
                    return;
                }
                else{
                    Toast.makeText(UserRegister.this,_G_username + "Successfull register",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}