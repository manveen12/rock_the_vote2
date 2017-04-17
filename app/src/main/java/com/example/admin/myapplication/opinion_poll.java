package com.example.admin.myapplication;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class opinion_poll extends AppCompatActivity {


        EditText poll_name,poll_detail_et, poll_date,poll_time_from,poll_time_to;
        Button  submit;
        String date ;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_opinion_poll);

           poll_name=(EditText)findViewById(R.id.name) ;
            poll_detail_et = (EditText) findViewById(R.id.details_et);

            poll_date = (EditText) findViewById(R.id.addpoll4);
            poll_time_from=(EditText)findViewById(R.id.addpoll5);
            poll_time_to=(EditText)findViewById(R.id.addpoll6);


        }

        public void add_poll(View v) {
            String poll_namee=poll_name.getText().toString();
            String poll_detail = poll_detail_et.getText().toString();

            String poll_from=poll_time_from.getText().toString();
            String poll_to=poll_time_to.getText().toString();

            if(poll_namee.equals(""))
            {
                Toast.makeText(opinion_poll.this , "please enter poll name" , Toast.LENGTH_SHORT).show();
                return;

            }
            if(poll_detail.equals(""))
            {
                Toast.makeText(opinion_poll.this , "please enter poll details" , Toast.LENGTH_SHORT).show();
                return;

            }
            if(poll_from.equals("")&& (poll_to.equals("")))
            {
                Toast.makeText(opinion_poll.this , "please enter time" , Toast.LENGTH_SHORT).show();
                return;

            }


            JSONObject job = new JSONObject();

            try {
                job.put("poll_name_key",poll_namee);
                job.put("poll_detail_key", poll_detail);
                 job.put("poll_date_key",date);
                job.put("poll_from_key",poll_from);
                job.put("poll_to_key",poll_to);
                job.put("poll_type", "opinion");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(job);

            JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/opinion_poll.php", job,
                    new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        if(response.getString("key").equals("1"))
                        {
                            Toast.makeText(opinion_poll.this , "poll added successfully" , Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        else {
                            Toast.makeText(opinion_poll.this , "error try again" , Toast.LENGTH_SHORT).show();

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

            jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

            AppController app = new AppController(opinion_poll.this);
            app.addToRequestQueue(jobreq);


        }

        public void open_calender(View v)
        {
            Calendar mcurrentDate = Calendar.getInstance();
            int year = mcurrentDate.get(Calendar.YEAR);
            int  month = mcurrentDate.get(Calendar.MONTH);
            int  day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            final DatePickerDialog mDatePicker = new DatePickerDialog(opinion_poll.this , new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker datepicker, int year, int month, int day) {

                   date = String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);

                    poll_date.setText(date);


                }
            }, year, month, day);
            mDatePicker.setTitle("Please select polling  date");

           // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());

            mDatePicker.show();

        }
    }
