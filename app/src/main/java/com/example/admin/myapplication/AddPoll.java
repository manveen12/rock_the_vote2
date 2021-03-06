package com.example.admin.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class AddPoll extends AppCompatActivity {

    EditText poll_question_et ,poll_date,poll_time_from,poll_time_to, option1 , option2 , option3 , option4 ;
    RadioButton radio_1,radio_2;
    String date,mnth;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poll);

        poll_question_et = (EditText) findViewById(R.id.details_et);
          poll_date=(EditText) findViewById(R.id.addpoll4);
        poll_time_from=(EditText)findViewById(R.id.addpoll5);
        poll_time_to=(EditText)findViewById(R.id.addpoll6);
        option1 = (EditText) findViewById(R.id.edit1);
        option2= (EditText)findViewById(R.id.edit2);
        option3= (EditText)findViewById(R.id.edit3);
        option4=(EditText)findViewById(R.id.edit4);
        radio_1=(RadioButton)findViewById(R.id.radio1);
        radio_2=(RadioButton)findViewById(R.id.radio2);
    }

    public void add_poll(View v)
    {
        String poll_detail = poll_question_et.getText().toString();

        String poll_from = poll_time_from.getText().toString();
        String poll_to = poll_time_to.getText().toString();
        String s_option1 = option1.getText().toString();
        String s_option2 = option2.getText().toString();
        String s_option3 = option3.getText().toString();
        String s_option4 = option4.getText().toString();


        if(poll_detail.equals(""))
        {
            Toast.makeText(AddPoll.this , "please enter poll question" , Toast.LENGTH_SHORT).show();
            return;

        }
        if(poll_date.equals(""))
        {
            Toast.makeText(AddPoll.this , "please select poll date" , Toast.LENGTH_SHORT).show();
            return;

        }
        if(poll_from.equals("") || (poll_to.equals("")))
        {
            Toast.makeText(AddPoll.this , "please select time" , Toast.LENGTH_SHORT).show();
            return;

        }

        if( (s_option1.equals("") || s_option2.equals("")) && radio_2.isChecked())
        {
            Toast.makeText(AddPoll.this , "please enter atleast two options" , Toast.LENGTH_SHORT).show();
            return;

        }

        SharedPreferences sp = getSharedPreferences("user_info" , MODE_PRIVATE);


        JSONObject job = new JSONObject();

        try {
            job.put("poll_question_key", poll_detail);
            job.put("poll_date_key",date);
            job.put("poll_timefrom_key", poll_from);
            job.put("poll_timeto_key", poll_to);
            job.put("option1" , s_option1);
            job.put("option2" , s_option2);
            job.put("option3", s_option3);
            job.put("option4",s_option4);
            job.put("aid", sp.getString("admin_id" , ""));

            if(radio_1.isChecked())
            {
                job.put("type", "opinion");
            }

            if(radio_2.isChecked())
            {
                job.put("type", "candidate");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/add_poll.php", job,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getString("key").equals("1"))
                            {
                                Toast.makeText(AddPoll.this , "poll added successfully" , Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(AddPoll.this, AdminExit.class);
                                startActivity(i);


                            }

                            else {
                                Toast.makeText(AddPoll.this , "error try again" , Toast.LENGTH_SHORT).show();

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

        AppController app = new AppController(AddPoll.this);
        app.addToRequestQueue(jobreq);


    }


    public void open_calender(View v) {
        Calendar mcurrentDate = Calendar.getInstance();
        int year = mcurrentDate.get(Calendar.YEAR);
        int month = mcurrentDate.get(Calendar.MONTH);
        int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog mDatePicker = new DatePickerDialog(AddPoll.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                int newmonth= month +1;
                if(newmonth<10){
                    mnth="0"+String.valueOf(newmonth);
                }

                date = String.valueOf(year) + "/" + String.valueOf(mnth) + "/" + String.valueOf(day);

                poll_date.setText(date);


            }
        }, year, month, day);
        mDatePicker.setTitle("Please select polling  date");

        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());

        mDatePicker.show();

    }

   public void from (View v){
       Calendar mcurrentTime = Calendar.getInstance();
       int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
       int minute = mcurrentTime.get(Calendar.MINUTE);
       TimePickerDialog mTimePicker;
       mTimePicker = new TimePickerDialog(AddPoll.this, new TimePickerDialog.OnTimeSetListener() {
           @Override
           public void onTimeSet(TimePicker view, int hourOfDay,
                                 int minute) {

               poll_time_from.setText(hourOfDay + ":" + minute);
           }
       }, hour, minute, false);
       mTimePicker.show();
   }

    public void to (View v){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddPoll.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {


                poll_time_to.setText(hourOfDay + ":" + minute);
            }
        }, hour, minute, false);
        mTimePicker.show();
    }

    public void candidate (View v)
    {
        DrawerLayout d = (DrawerLayout) findViewById(R.id.drawer_layout);

        d.openDrawer(Gravity.RIGHT);

    }
}










