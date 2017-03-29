package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UserLogin extends AppCompatActivity {
    EditText userNameBox, passwordBox;
    Button loginBox, registerBox;
    TextView textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        userNameBox = (EditText) findViewById(R.id.edit1);

        passwordBox = (EditText) findViewById(R.id.edit2);}



    public void login(View v)
    {
        String email = userNameBox.getText().toString();

        String password = passwordBox.getText().toString();

        if(email.equals(""))
        {
            Toast.makeText(UserLogin.this , "please enter your email" , Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals(""))
        {
            Toast.makeText(UserLogin.this , "please enter your password" , Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();

        try {
            job.put("email_key" , email);
            job.put("password" , password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.1.102/rock_the_vote/user_login.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getString("key").equals("done"))
                            {
                                Intent i = new Intent(UserLogin.this, userSignup.class);
                                startActivity(i);

                            }

                            else {
                                Toast.makeText(UserLogin.this , "error" , Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

        AppController app = new AppController(UserLogin.this);
        app.addToRequestQueue(jobjreq);

        {
            Intent i = new Intent(UserLogin.this,HomePage.class);
            startActivity(i);
        }

    }

    ;



}




