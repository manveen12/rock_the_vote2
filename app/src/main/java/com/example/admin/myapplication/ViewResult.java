package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class ViewResult extends AppCompatActivity {
    RecyclerView r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        // finding view by id for recycler view in activity main layout
        r = (RecyclerView) findViewById(R.id.recycler_id);

        // call get_data function
        get_data();
    }





    public void get_data()
    {
        // making json array request to get json array data
        JsonArrayRequest jsonreq = new JsonArrayRequest("http://"+Ipadress.ip+"/rock_the_vote/recycler_result.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                // making object of adapter class and passing json array and Activity to adapter constructer
               result_adapter ad = new result_adapter(response ,ViewResult.this);


                // setting properties for recycler view like how it scroll vertically , horizontally
                r.setLayoutManager(new LinearLayoutManager(ViewResult.this , LinearLayoutManager.VERTICAL,false));

                // setting adapter ad to recycler view r
                r.setAdapter(ad);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

        AppController app = new AppController(ViewResult.this);

        app.addToRequestQueue(jsonreq);
    }

}
