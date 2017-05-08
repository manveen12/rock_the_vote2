package com.example.admin.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class password_homepage extends AppCompatActivity {
EditText expasswordBox,passwordBox,confirmpasswordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_homepage);
        passwordBox = (EditText) findViewById(R.id.edit2);
       confirmpasswordBox = (EditText) findViewById(R.id.edit3);
       expasswordBox=(EditText)findViewById(R.id.edit1);
    }
    public void change_pass(View v)
    {
        final String pass = passwordBox.getText().toString();

        String confirm_pass = confirmpasswordBox.getText().toString();
        String ex_pass = expasswordBox.getText().toString();

        if (ex_pass.equals("")) {
            Toast.makeText(password_homepage.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.equals("")) {
            Toast.makeText(password_homepage.this, "please enter new password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirm_pass.equals("")) {
            Toast.makeText(password_homepage.this, "please confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(confirm_pass)) {
            Toast.makeText(password_homepage.this, "password and confirm password does not match", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject job = new JSONObject();
        SharedPreferences sp =password_homepage.this.getSharedPreferences("user_info" , MODE_PRIVATE);

        String type = sp.getString("type_key" , "");
        String email =  sp.getString("email", "");

        try {
            job.put("password_key", getIntent().getStringExtra("password"));
            job.put("password", pass);
            job.put("type",type);
            job.put("email",email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/update_pass.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getString("key").equals("done")) {

                                // code to save value through out app

                                Toast.makeText(password_homepage.this, "password updated", Toast.LENGTH_SHORT).show();

                                finish();


                            } else
                            {
                                Toast.makeText(password_homepage.this, "existing password is incorrect", Toast.LENGTH_SHORT).show();
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

        AppController app = new AppController(password_homepage.this);
        app.addToRequestQueue(jobjreq);
    }
}


