package com.example.admin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
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

public class AdminLogin extends AppCompatActivity {
    EditText userNameBox, passwordBox;
    Button loginBox, registerBox;
    TextView textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        userNameBox = (EditText) findViewById(R.id.edit1);

        passwordBox = (EditText) findViewById(R.id.edit2);
        loginBox = (Button) findViewById(R.id.login_btn);
    }


    public void login(View v) {
        final String email = userNameBox.getText().toString();

        String password = passwordBox.getText().toString();
        if (email.equals("")) {
            Toast.makeText(AdminLogin.this, "please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(AdminLogin.this, "please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(AdminLogin.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();

        try {
            job.put("email_key", email);
            job.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/admin_login.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getString("key").equals("done")) {

                                // code to save value through out app

                                SharedPreferences.Editor sp = getSharedPreferences("user_info" , MODE_PRIVATE).edit();
                                sp.putString("type_key" , "admin");
                                sp.putString("email" , email);
                                sp.putString("admin_id" ,response.getString("admin_id") );
                                sp.commit();

                                    Intent i = new Intent(AdminLogin.this, AdminOptions.class);
                                    startActivity(i);


                            } else
                            {
                                Toast.makeText(AdminLogin.this, "password and email does not match", Toast.LENGTH_SHORT).show();
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

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(AdminLogin.this);
        app.addToRequestQueue(jobjreq);



    }



    public void opensignup(View v) {
        Intent i = new Intent(AdminLogin.this, AdminSignup.class);
        startActivity(i);

    }
    public void forget(View v) {
        Intent i = new Intent(AdminLogin.this, forget_password.class);
        startActivity(i);

    }
}
