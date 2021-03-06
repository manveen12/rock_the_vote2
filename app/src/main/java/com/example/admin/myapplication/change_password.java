package com.example.admin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class change_password extends AppCompatActivity {
EditText passwordBox,confirmpasswordBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        passwordBox = (EditText) findViewById(R.id.edit1);
        confirmpasswordBox = (EditText) findViewById(R.id.edit2);
    }


    public void change_pass(View v)
    {
        final String pass = passwordBox.getText().toString();

        String confirm_pass = confirmpasswordBox.getText().toString();


        if (pass.equals("")) {
            Toast.makeText(change_password.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirm_pass.equals("")) {
            Toast.makeText(change_password.this, "please confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(confirm_pass)) {
            Toast.makeText(change_password.this, "password and confirm password does not match", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject job = new JSONObject();

        try {
            job.put("email_key", getIntent().getStringExtra("email"));
            job.put("password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/update_admin_pass.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getString("key").equals("done")) {

                                // code to save value through out app

                                Toast.makeText(change_password.this, "password updated", Toast.LENGTH_SHORT).show();

                                finish();


                            } else
                            {
                                Toast.makeText(change_password.this, "password and email does not match", Toast.LENGTH_SHORT).show();
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

        AppController app = new AppController(change_password.this);
        app.addToRequestQueue(jobjreq);
    }
}
