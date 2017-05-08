package com.example.admin.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.type;

public class Update_profile extends AppCompatActivity {
    EditText name_et, email_et, age_et, occu_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        name_et = (EditText) findViewById(R.id.edit_name);
        email_et = (EditText) findViewById(R.id.edit_email);
        age_et = (EditText) findViewById(R.id.edit_age);
        occu_et = (EditText) findViewById(R.id.edit_occu);
        get_values();
    }

    public void get_values() {
        JSONObject jobj = new JSONObject();

        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);

        String email = sp.getString("email", "");

        String type = sp.getString("type_key", "");

        try {
            jobj.put("email_key", email);
            jobj.put("type_key", type);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/get_data.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr = response.getJSONArray("result");

                    JSONObject job_box = (JSONObject) jarr.get(0);
                    email_et.setText(job_box.getString("email"));
                    name_et.setText(job_box.getString("username"));
                    age_et.setText(job_box.getString("age"));
                    occu_et.setText(job_box.getString("occupation"));


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
        AppController app = new AppController(Update_profile.this);
        app.addToRequestQueue(jobreq);
    }


    public void update(View v) {
        String name = name_et.getText().toString();
        String age = age_et.getText().toString();
        String occupation = occu_et.getText().toString();

        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        String email = sp.getString("email", "");

        String type = sp.getString("type_key", "");


        JSONObject job = new JSONObject();

        try {
            job.put("email_key",email);
            job.put("type_key",type);
            job.put("name_key", name);
            job.put("age_key", age);
            job.put("occu_key", occupation);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Ipadress.ip + "/rock_the_vote/update_profile.php", job, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {

                        JSONArray jarr = response.getJSONArray("result");

                        JSONObject job_box = (JSONObject) jarr.get(0);
                        email_et.setText(job_box.getString("email"));
                        name_et.setText(job_box.getString("name"));
                        age_et.setText(job_box.getString("age"));
                        occu_et.setText(job_box.getString("occupation"));


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
        AppController app = new AppController(Update_profile.this);
        app.addToRequestQueue(jobreq);
    }

}
