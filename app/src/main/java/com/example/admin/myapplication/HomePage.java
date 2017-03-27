package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    ArrayList<String> poll_list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        poll_list = new ArrayList<String >();

        poll_list.add("poll 1");
        poll_list.add("poll 2");
        poll_list.add("poll 3");
        poll_list.add("poll 4");
        poll_list.add("poll 5");


    }
}
