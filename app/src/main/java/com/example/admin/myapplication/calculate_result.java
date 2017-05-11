package com.example.admin.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class calculate_result extends AppCompatActivity {
    RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_result);
        r = (RecyclerView) findViewById(R.id.calculate_id);

        // call get_data function
        get_data();
    }

    public void get_data() {
        // making json array request to get json array data

        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);

        try {
            job.put("admin_key", sp.getString("admin_id", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonreq = new JsonObjectRequest("http://" + Ipadress.ip + "/rock_the_vote/calculate_result.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jarr = response.getJSONArray("result");
                    // making object of adapter class and passing json array and Activity to adapter constructer
                    calculate_adapter ad = new calculate_adapter(jarr, calculate_result.this);


                    // setting properties for recycler view like how it scroll vertically , horizontally
                    r.setLayoutManager(new LinearLayoutManager(calculate_result.this, LinearLayoutManager.VERTICAL, false));

                    // setting adapter ad to recycler view r
                    r.setAdapter(ad);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(calculate_result.this);

        app.addToRequestQueue(jsonreq);
    }


    public static void calculate_result(String poll_id , final  Activity a)
    {
        JSONObject job = new JSONObject();



        try {
            job.put("poll_key", poll_id);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonreq = new JsonObjectRequest("http://" + Ipadress.ip + "/rock_the_vote/calculate_result.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jarr = response.getJSONArray("result");
                    // making object of adapter class and passing json array and Activity to adapter constructer

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(a);

        app.addToRequestQueue(jsonreq);

    }
}


