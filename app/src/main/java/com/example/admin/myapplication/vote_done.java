package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class vote_done extends AppCompatActivity {
Button homeBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_done);
        homeBox = (Button) findViewById(R.id.home_btn);
        View.OnClickListener btclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent i = new Intent(vote_done.this, HomePage.class);

                    startActivity(i);


                }

            }

        };
        homeBox.setOnClickListener(btclick);
    }
    }

