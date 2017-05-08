package com.example.admin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class comment_vote extends AppCompatActivity {
    EditText commentBox;
    RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_vote);
        r = (RecyclerView) findViewById(R.id.recyclerr_id);
      commentBox=(EditText)findViewById(R.id.write_comment);

        // call get_data function
        comment();
    }
    public void comment()
    {
        JSONObject job = new JSONObject();

        try {
            job.put("poll_id", getIntent().getStringExtra("poll_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // making json array request to get json array data
        JsonObjectRequest jsonreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/view_comment.php", job , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jarr = null;
                try {
                    jarr = response.getJSONArray("result");
                    // making object of adapter class and passing json array and Activity to adapter constructer
                    comment_adapter ad = new comment_adapter(jarr ,comment_vote.this);


                    // setting properties for recycler view like how it scroll vertically , horizontally
                    r.setLayoutManager(new LinearLayoutManager(comment_vote.this , LinearLayoutManager.VERTICAL,false));

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


        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

        AppController app = new AppController(comment_vote.this);

        app.addToRequestQueue(jsonreq);
    }

    public void post_comment(View view) {
        String write_comment = commentBox.getText().toString();
        if (write_comment.equals("")) {
            Toast.makeText(comment_vote.this, "please enter comment", Toast.LENGTH_SHORT).show();
            return;

        }


        JSONObject job = new JSONObject();
        SharedPreferences sp = getSharedPreferences("user_info",MODE_PRIVATE);

        String email = sp.getString("email", "");
        try {
            job.put("poll_id", getIntent().getStringExtra("poll_id"));
            job.put("comment_key", write_comment);
            job.put("user_email",email);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipadress.ip+"/rock_the_vote/add_comment.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getString("key").equals("done")) {

                                // code to save value through out app


                                Toast.makeText(comment_vote.this, "comment posted successfully", Toast.LENGTH_SHORT).show();

                                commentBox.getText().clear();
                                comment();



                            } else
                            {
                                Toast.makeText(comment_vote.this, "error", Toast.LENGTH_SHORT).show();
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

        AppController app = new AppController(comment_vote.this);
        app.addToRequestQueue(jobjreq);

    }
}





