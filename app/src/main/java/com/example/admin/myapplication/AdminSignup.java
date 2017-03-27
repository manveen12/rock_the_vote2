package com.example.admin.myapplication;

import android.content.Intent;
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

public class AdminSignup extends AppCompatActivity {
    EditText usernameBox , emailBox,passwordBox,cpasswordBox;
    Button buttonBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        usernameBox = (EditText)findViewById(R.id.user);
        emailBox=(EditText)findViewById(R.id.email);
        passwordBox=(EditText)findViewById(R.id.pass);
        buttonBox=(Button)findViewById(R.id.register);
        cpasswordBox=(EditText)findViewById(R.id.cpass) ;
        View.OnClickListener btn_click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name =usernameBox.getText().toString();
                String email=emailBox.getText().toString();
                String password=passwordBox.getText().toString();
                String cpassword=cpasswordBox.getText().toString();

                if(name.equals(""))
                {
                    Toast.makeText(AdminSignup.this,"please enter name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.equals("")) {
                    Toast.makeText(AdminSignup.this, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals("")) {
                    Toast.makeText(AdminSignup.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cpassword.equals(""))
                {Toast.makeText(AdminSignup.this,"please confirm password",Toast.LENGTH_SHORT).show();
                return;
                }
                if(!cpassword.equals(password))
                {
                    Toast.makeText(AdminSignup.this,"password do not match",Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject jobj = new JSONObject();

                try {
                    jobj.put("name" , name);
                    jobj.put("email" , email);
                    jobj.put("pass",password);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println(jobj);

                JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.1.102/rock_the_vote/admin_signup.php",jobj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getString("key").equals("0"))
                            {
                                Toast.makeText(AdminSignup.this ,"email already exist" , Toast.LENGTH_SHORT).show();

                            }
                            else if(response.getString("key").equals("1")) {
                                Toast.makeText(AdminSignup.this ,"done" , Toast.LENGTH_SHORT).show();

                            }

                            else {
                                Toast.makeText(AdminSignup.this ,"error" , Toast.LENGTH_SHORT).show();

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

              jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000,3,2))  ;
                AppController app=new AppController(AdminSignup.this);
                app.addToRequestQueue(jobjreq);
            }
        };
        buttonBox.setOnClickListener(btn_click);
    }
}


