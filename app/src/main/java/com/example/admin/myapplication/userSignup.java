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

public class userSignup extends AppCompatActivity {
    EditText usernameBox , emailBox,passwordBox,cpasswordBox;
    Button buttonBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        usernameBox = (EditText)findViewById(R.id.user);
        emailBox=(EditText)findViewById(R.id.email);
        passwordBox=(EditText)findViewById(R.id.pass);
        cpasswordBox=(EditText) findViewById(R.id.cpass);
        buttonBox=(Button)findViewById(R.id.register);
        View.OnClickListener btn_click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =usernameBox.getText().toString();
                String email=emailBox.getText().toString();
                String password=passwordBox.getText().toString();
                String cpassword=cpasswordBox.getText().toString();

                if(name.equals(""))
                {
                    Toast.makeText(userSignup.this,"please enter name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.equals("")) {
                    Toast.makeText(userSignup.this, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals("")) {
                    Toast.makeText(userSignup.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cpassword.equals(""))
                {Toast.makeText(userSignup.this,"please confirm password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!cpassword.equals(password))
                {
                    Toast.makeText(userSignup.this,"password do not match",Toast.LENGTH_SHORT).show();
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
                JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.1.102/rock_the_vote/user_signup.php",jobj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000,3,2))  ;
                AppController app=new AppController(userSignup.this);
                app.addToRequestQueue(jobjreq);
            }
        };
        buttonBox.setOnClickListener(btn_click);
    }
}





