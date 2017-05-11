package com.example.admin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Admin on 10-04-2017.
 */

public class vote_fragment extends Fragment {
    EditText emailBox,pollNoBox, passwordBox;
    Button proceedBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.vote_layout ,container ,false);
         emailBox=(EditText)v.findViewById(R.id.edit1);
        pollNoBox=(EditText)v.findViewById(R.id.edit);
        passwordBox=(EditText)v.findViewById(R.id.edit2);
        proceedBox=(Button)v.findViewById(R.id.login_btn);

        proceedBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyDetails();
            }
        });
        return v;
    }
    public void verifyDetails()
    {

        String email=emailBox.getText().toString();
        String password=passwordBox.getText().toString();
        final String poll=pollNoBox.getText().toString();

        if (email.equals("")) {
            Toast.makeText(getActivity(), "please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(getActivity(), "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (poll.equals("")) {
            Toast.makeText(getActivity(), "please enter poll number", Toast.LENGTH_SHORT).show();
            return;
        }



        JSONObject job = new JSONObject();
        SharedPreferences sp =getActivity().getSharedPreferences("user_info" , MODE_PRIVATE);


        String type = sp.getString("type_key" , "");

        try {
            job.put("email_key", email);
            job.put("password", password);
            job.put("poll_key",poll);
            job.put("type",type);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/vote_login.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getString("key").equals("done")) {



                               JSONObject jobj =  response.getJSONObject("poll_details");
                                Intent i = new Intent(getActivity(), cast_vote.class);

                                i.putExtra("poll_data",jobj.toString());
                                i.putExtra("poll_id" , poll);


                                startActivity(i);


                            } else if(response.getString("key").equals("invalid data"))
                            {
                                Toast.makeText(getActivity(), "email and password does not match", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else if (response.getString("key").equals("not started"))
                            {
                                Toast.makeText(getActivity(), "poll not started", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            else if (response.getString("key").equals("expires"))
                            {
                                Toast.makeText(getActivity(), "poll expires", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            else if (response.getString("key").equals("casted"))
                            {
                                Toast.makeText(getActivity(), "already  voted for this poll", Toast.LENGTH_SHORT).show();
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

        AppController app = new AppController(getActivity());
        app.addToRequestQueue(jobjreq);



    }




}






