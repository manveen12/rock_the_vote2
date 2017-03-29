package com.example.admin.myapplication;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;




public class AddPoll extends AppCompatActivity {
    EditText pollBox, quesBox, edit1Box, edit2Box, edit3Box, edit4Box;
    Button submitBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poll);
        pollBox = (EditText) findViewById(R.id.name);

        quesBox = (EditText) findViewById(R.id.ques);


        submitBox = (Button) findViewById(R.id.submit);

    }
}
