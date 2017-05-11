package com.example.admin.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Admin on 09-05-2017.
 */

public class calculate_view_holder extends RecyclerView.ViewHolder

{

    public TextView poll_id, question_id;

    Button cal_id;

    public calculate_view_holder(View itemView) {
        super(itemView);
        poll_id = (TextView) itemView.findViewById(R.id.poll_id);

        question_id = (TextView) itemView.findViewById(R.id.ques_id);

        cal_id = (Button) itemView.findViewById(R.id.cal);

    }
}
