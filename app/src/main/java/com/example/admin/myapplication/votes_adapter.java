package com.example.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 18-04-2017.
 */

public class votes_adapter extends RecyclerView.Adapter< votes_view_holder> {
    // creating JSONArray variable globally
    JSONArray jsarr;

    // creating Activity variable globally
    Activity a;

    public votes_adapter(JSONArray jsarr , Activity a)
    {
        this.jsarr = jsarr;

        this.a = a;
    }
    @Override
    public votes_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        votes_view_holder view = new votes_view_holder(LayoutInflater.from(a).inflate(R.layout.home_cell,parent,false));

        return view;

    }

    @Override
    public void onBindViewHolder(votes_view_holder holder, int position) {
        try {
            // iterating for each json object in json array
            final JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder
            holder.no_id.setText(job.getString("Pid"));
            holder.type_id.setText(job.getString("poll_type"));
            holder.ques_id.setText(job.getString("poll_question"));
            holder.from_id.setText(job.getString("time_from"));
            holder.to_id.setText(job.getString("time_to"));
            holder.date_id.setText(job.getString("date"));
            holder.comment_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(a,comment_vote.class);

                    try {
                        i.putExtra("poll_id", job.getString("Pid") );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    a.startActivity(i);
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
