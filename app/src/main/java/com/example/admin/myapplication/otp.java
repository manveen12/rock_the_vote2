package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class otp extends AppCompatActivity {

    String email ;
    String pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        email = getIntent().getStringExtra("email_key");
        pin = getIntent().getStringExtra("pin_key");

        Toast.makeText(otp.this, pin, Toast.LENGTH_SHORT).show();

    }

    public void verify_otp(View view) {


        EditText otpBox = (EditText) findViewById(R.id.edit2);

        final String otp =otpBox.getText().toString();

        if(otp.equals(pin))
        {
            Intent i = new Intent(otp.this , change_password.class);

            i.putExtra("email" , email);

            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(otp.this, "invalid otp", Toast.LENGTH_SHORT).show();


        }

    }
}
