package com.example.admin.myapplication;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Admin on 18-04-2017.
 */


public class votes_view_holder extends RecyclerView.ViewHolder {
    public   TextView no_id,type_id,ques_id,from_id,to_id,date_id;
    public Button like_id,dislike_id,comment_id;
    public votes_view_holder(View itemView) {
        super(itemView);
        no_id=(TextView)itemView.findViewById(R.id.no_id);
        type_id=(TextView)itemView.findViewById(R.id.type_id);
        ques_id = (TextView) itemView.findViewById(R.id.ques_id);
        from_id = (TextView) itemView.findViewById(R.id.from_id);
        to_id = (TextView) itemView.findViewById(R.id.to_id);
        date_id = (TextView) itemView.findViewById(R.id.date_id);
        comment_id=(Button)itemView.findViewById(R.id.comment_id);
    }
}
