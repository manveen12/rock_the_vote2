package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.admin.myapplication.R.id.user_btn;

public class MultiUser extends AppCompatActivity {
    Button adminBox, userBox, resultBox;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_user);


        adminBox = (Button) findViewById(R.id.admin_btn);


        View.OnClickListener btclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent i = new Intent(MultiUser.this, AdminLogin.class);

                    startActivity(i);


                }

            }

        };


        adminBox.setOnClickListener(btclick);


        userBox = (Button) findViewById(R.id.user_btn);
        View.OnClickListener btclick1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent i = new Intent(MultiUser.this, UserLogin.class);

                    startActivity(i);


                }

            }

        };
        userBox.setOnClickListener(btclick1);

        resultBox = (Button) findViewById(R.id.result_btn);
        View.OnClickListener btclick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent i = new Intent(MultiUser.this, ViewResult.class);

                    startActivity(i);


                }

            }

        };
        resultBox.setOnClickListener(btclick2);
    }
}