package com.example.admin.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Admin on 19-04-2017.
 */

public class result_view_holder extends RecyclerView.ViewHolder {
    public TextView ques_id,result_id,date_id;

    public result_view_holder(View itemView) {
        super(itemView);
        ques_id = (TextView) itemView.findViewById(R.id.ques_id);

        result_id = (TextView) itemView.findViewById(R.id.result_id);
        date_id = (TextView) itemView.findViewById(R.id.date_id);
    }
}

