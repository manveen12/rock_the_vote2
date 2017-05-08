package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.admin.myapplication.R.id.email;

public class user_otp extends AppCompatActivity {
    String email ;
    String pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_otp);

            email = getIntent().getStringExtra("email_key");
            pin = getIntent().getStringExtra("pin_key");

            Toast.makeText(user_otp.this, "email not found", Toast.LENGTH_SHORT).show();

        }
    public void verify_otp(View view) {


        EditText otpBox = (EditText) findViewById(R.id.edit2);

        final String otp =otpBox.getText().toString();

        if(otp.equals(pin))
        {
            Intent i = new Intent(user_otp.this , change_password.class);

            i.putExtra("email" , email);

            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(user_otp.this, "invalid otp", Toast.LENGTH_SHORT).show();


        }

    }
}

