package com.example.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import static android.support.v7.widget.LinearLayoutManager.*;

/**
 * Created by Admin on 10-04-2017.
 */

public class home_fragment extends Fragment

{
    RecyclerView r;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_layout ,container ,false);
        // finding view by id for recycle view in activity main layout
        r = (RecyclerView) v.findViewById(R.id.recycle_id);

        // call get_data function
        get_data();

        return v;
    }


    public void get_data()
        {
            // making json array request to get json array data
            JsonArrayRequest jsonreq = new JsonArrayRequest("http://"+Ipadress.ip+"/rock_the_vote/recyler_vote.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    // making object of adapter class and passing json array and Activity to adapter constructer
                    votes_adapter ad = new votes_adapter(response , getActivity());


                    // setting properties for recycler view like how it scroll vertically , horizontally
                    r.setLayoutManager(new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL,false));

                    // setting adapter ad to recycler view r
                    r.setAdapter(ad);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    System.out.println(error);

                }
            });


            jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

            AppController app = new AppController(getActivity());

            app.addToRequestQueue(jsonreq);

    }

}
