package com.example.admin.myapplication;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 19-04-2017.
 */

public class result_adapter extends RecyclerView.Adapter<result_view_holder> {
    // creating JSONArray variable globally
    JSONArray jsarr;

    // creating Activity variable globally
    Activity a;

    public result_adapter(JSONArray jsarr , Activity a)
    {
        this.jsarr = jsarr;

        this.a = a;
    }

    @Override
    public result_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        result_view_holder v = new result_view_holder(LayoutInflater.from(a).inflate(R.layout.result_cell,parent,false));

        return v;
    }

    @Override
    public void onBindViewHolder(result_view_holder holder, int position) {
        try {
            // iterating for each json object in json array
            JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder
            holder.ques_id.setText(job.getString("poll_question"));

            holder.result_id.setText(job.getString("results"));
            holder.date_id.setText(job.getString("date"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return jsarr.length();
    }
}

