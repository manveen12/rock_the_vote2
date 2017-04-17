package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminOptions extends AppCompatActivity {
    Button addpollBox, changepollBox, homeBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);
        addpollBox = (Button) findViewById(R.id.addpoll);


        View.OnClickListener btclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent i = new Intent(AdminOptions.this, Which_poll.class);

                    startActivity(i);


                }

            }

        };


        addpollBox.setOnClickListener(btclick);


        changepollBox = (Button) findViewById(R.id.settingpoll);
        View.OnClickListener btclick1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent i = new Intent(AdminOptions.this, Which_poll.class);

                    startActivity(i);


                }

            }

        };
        changepollBox.setOnClickListener(btclick1);

        homeBox = (Button) findViewById(R.id.home_btn);
        View.OnClickListener btclick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent i = new Intent(AdminOptions.this,HomePage.class);

                    startActivity(i);


                }

            }

        };
        homeBox.setOnClickListener(btclick2);
    }

}

