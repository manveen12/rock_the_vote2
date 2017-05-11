package com.example.admin.myapplication;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 09-05-2017.
 */

public class calculate_adapter extends RecyclerView.Adapter <calculate_view_holder> {

    JSONArray jsarr;

    // creating Activity variable globally
    Activity a;


    public calculate_adapter(JSONArray jsarr, Activity a) {
        this.jsarr = jsarr;

        this.a = a;
    }

    @Override
    public calculate_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        calculate_view_holder v = new calculate_view_holder(LayoutInflater.from(a).inflate(R.layout.calculate_cell,parent,false));

        return v;
    }

    @Override
    public void onBindViewHolder(calculate_view_holder holder, int position) {
        try {
            // iterating for each json object in json array
           final   JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder

            holder.poll_id.setText(job.getString("Pid"));

            holder.question_id.setText(job.getString("poll_question"));

            holder.cal_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        calculate_result.calculate_result(job.getString("Pid") ,a);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsarr.length();
    }
    }
