package com.example.admin.myapplication;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 26-04-2017.
 */

public class comment_adapter  extends RecyclerView.Adapter<comment_view_holder>{
    JSONArray jsarr;

    // creating Activity variable globally
    Activity a;
    public comment_adapter(JSONArray jsarr , Activity a)
    {
        this.jsarr = jsarr;

        this.a = a;
    }
    @Override
    public comment_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        comment_view_holder v = new comment_view_holder(LayoutInflater.from(a).inflate(R.layout.comment_cell,parent,false));
        return v;
    }

    @Override
    public void onBindViewHolder(comment_view_holder holder, int position) {
        try {
            // iterating for each json object in json array
            JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder
            holder.comment_id.setText(job.getString("comment"));
            holder.email_id.setText(job.getString("email_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    @Override
    public int getItemCount() {
        return jsarr.length();
    }
}
