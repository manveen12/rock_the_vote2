package com.example.admin.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Admin on 26-04-2017.
 */

public class comment_view_holder  extends RecyclerView.ViewHolder {
    public TextView email_id, comment_id;

    public comment_view_holder(View itemView) {
        super(itemView);
        email_id = (TextView) itemView.findViewById(R.id.email_id);

        comment_id = (TextView) itemView.findViewById(R.id.comment_id);

    }

}