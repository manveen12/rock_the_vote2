package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Which_poll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_which_poll);
    }


    public void open_opinion_poll(View v)
    {
Intent i=new Intent(Which_poll.this , opinion_poll.class);

        startActivity(i);

    }

    public void open_candidate_poll (View v)
    {
        Intent i = new Intent(Which_poll.this , AddPoll.class);

        startActivity(i);
    }
}
