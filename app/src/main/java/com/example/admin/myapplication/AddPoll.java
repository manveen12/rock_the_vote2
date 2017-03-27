package com.example.admin.myapplication;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPoll extends AppCompatActivity {
    EditText pollBox,quesBox;
    Button submitBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poll);
        pollBox = (EditText) findViewById(R.id.name);

        quesBox = (EditText) findViewById(R.id.ques);

        submitBox = (Button) findViewById(R.id.submit);

        View.OnClickListener btn_click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String poll = pollBox.getText().toString();
                String ques = quesBox.getText().toString();



                if(!poll.equals("") && !ques.equals(""))
                {
                    Intent i = new Intent(AddPoll.this , AdminExit.class);

                    startActivity(i);

                    finish();
                }
                else {
                    Toast.makeText(AddPoll.this,"enter input",Toast.LENGTH_SHORT).show();
                }
            }
        };

        submitBox.setOnClickListener(btn_click);

    }
}
