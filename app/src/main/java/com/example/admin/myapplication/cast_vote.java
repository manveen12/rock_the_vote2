package com.example.admin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class cast_vote extends AppCompatActivity {
TextView quesBox;
    RadioButton radio1Box,radio2Box,radio3Box,radio4Box,radio5Box,radio6Box;
    RadioGroup radiogroup , radioGroup_opinion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_vote);
        quesBox=(TextView)findViewById(R.id.ques);
        radio1Box=(RadioButton)findViewById(R.id.radio1);
        radio2Box=(RadioButton)findViewById(R.id.radio2);
        radio3Box=(RadioButton)findViewById(R.id.radio3);
        radio4Box=(RadioButton)findViewById(R.id.radio4);
        radio5Box=(RadioButton)findViewById(R.id.radio1_opinion);
        radio6Box= (RadioButton)findViewById(R.id.radio2_opinion);
        radiogroup = (RadioGroup) findViewById(R.id.radio_grp);
        radioGroup_opinion = (RadioGroup) findViewById(R.id.radio_grp_opinion);

        try {
            JSONObject job = new JSONObject(getIntent().getStringExtra("poll_data"));

            if(job.getString("poll_o1").equals(""))
            {
                radiogroup.setVisibility(View.GONE);
            }
            else {
                radioGroup_opinion.setVisibility(View.GONE);
            }

            quesBox.setText(job.getString("poll_question"));
            radio1Box.setText(job.getString("poll_o1"));
            radio2Box.setText(job.getString("poll_o2"));

            if(job.getString("poll_o3").equals(""))
            {
                radio3Box.setVisibility(View.GONE);
            }

            if(job.getString("poll_o4").equals(""))
            {
                radio4Box.setVisibility(View.GONE);
            }
            radio3Box.setText(job.getString("poll_o3"));
            radio4Box.setText(job.getString("poll_o4"));
            radio5Box.setText("yes");
            radio6Box.setText("no");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void submit(View v) {

        String answer = "";
        if (radio1Box.isChecked()) {
          answer = radio1Box.getText().toString();
        } else if (radio2Box.isChecked()) {
            answer = radio2Box.getText().toString();
        } else if (radio3Box.isChecked()) {
            answer = radio3Box.getText().toString();
        } else if (radio4Box.isChecked()) {
            answer = radio4Box.getText().toString();
        } else if (radio5Box.isChecked()) {
            answer = radio5Box.getText().toString();
        } else {
             answer = radio6Box.getText().toString();
        }

        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);


        String type = sp.getString("type_key", "");
        String email = sp.getString("email" , "");


        String poll_id = getIntent().getStringExtra("poll_id");

        JSONObject job = new JSONObject();
        try {
            job.put("user_type" , type);
            job.put("email" , email);
            job.put("poll_id",poll_id);
            job.put("answer",answer);
            if(type.equals("admin"))
            {
                job.put("admin_id",sp.getString("admin_id", ""));
            }
            else {
                job.put("user_id",sp.getString("user_id", ""));
            }
        } catch (JSONException e) {
            e.printStackTrace();

        }

        System.out.println(job);
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/cast_vote.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getString("key").equals("done")) {

                                            Intent i = new Intent(cast_vote.this, vote_done.class);

                                            startActivity(i);

                                        }

                            else
                            {
                                Toast.makeText(cast_vote.this, "error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(cast_vote.this);
        app.addToRequestQueue(jobjreq);



    }




}




