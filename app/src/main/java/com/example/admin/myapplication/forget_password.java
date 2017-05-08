package com.example.admin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
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

public class forget_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    public void next(View view) {

        EditText emailBox = (EditText) findViewById(R.id.edit1);

        final String email =emailBox.getText().toString();

        verify_email(email);


    }

    private void verify_email(final String email)
    {


        JSONObject job = new JSONObject();

        try {
            job.put("email_key", email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
 System.out.println(job);
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/verify_email.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                        try {
                            if (response.getString("key").equals("done")) {

                                // code to save value through out app

                                int pin =   (int)(Math.random()*9000)+1000 ;

                                Intent i = new Intent(forget_password.this , otp.class);

                                i.putExtra("email_key" , email);
                                i.putExtra("pin_key" , String.valueOf(pin));

                                startActivity(i);
                                finish();


                            } else
                            {
                                Toast.makeText(forget_password.this, "email not found", Toast.LENGTH_SHORT).show();
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

        AppController app = new AppController(forget_password.this);
        app.addToRequestQueue(jobjreq);


    }
}
