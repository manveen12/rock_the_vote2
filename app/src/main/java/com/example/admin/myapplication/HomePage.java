package com.example.admin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.admin.myapplication.R.id.update_profile;

public class HomePage extends AppCompatActivity {
    FragmentManager fm;
    TextView user_email , user_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        fm = getSupportFragmentManager();

        user_email = (TextView) findViewById(R.id.user_email);

        user_type = (TextView) findViewById(R.id.user_type);

        // code to get saved values through out app

        SharedPreferences sp = getSharedPreferences("user_info" , MODE_PRIVATE);

        String email =  sp.getString("email", "");

        String type = sp.getString("type_key" , "");

        // setting text to user text in side option pane
        user_email.setText(email);

        user_type.setText(type);


    }

    public void openhome(View v)
    {

        FragmentTransaction ft = fm.beginTransaction();
        Fragment home_frag = new home_fragment();

        ft.replace(R.id.frame_id ,home_frag);

        ft.commit();

    }

    public void openvote(View v)
    {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment vote_frag = new vote_fragment();
        ft.replace(R.id.frame_id , vote_frag);

        ft.commit();

    }

    public void openresults(View v)
    {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment result_frag = new result_fragment();

        ft.replace(R.id.frame_id , result_frag);


        ft.commit();
    }


    public void show_options(View v)
    {
        DrawerLayout d = (DrawerLayout) findViewById(R.id.drawer_layout);

        d.openDrawer(Gravity.LEFT);
    }
public void doNothing(View v)
{

}
public  void update_profile(View v)
{
    Intent i =new Intent(HomePage.this,Update_profile.class);
    startActivity(i);
}
    public void share (View v)
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download the app 'Rock the Vote' via play store now...";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    public void rate(View v){
           try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + this.getPackageName())));
            } catch (android.content.ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
            }
        }

    public void log_out (View v){
        Intent intent = new Intent(HomePage.this, MultiUser.class);
        startActivity(intent);
        finish();
    }
    public void change_pass (View v){
        Intent intent = new Intent(HomePage.this, password_homepage.class);
        startActivity(intent);
        finish();
    }
}
