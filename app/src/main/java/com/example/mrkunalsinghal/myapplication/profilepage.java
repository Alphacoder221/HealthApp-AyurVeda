package com.example.mrkunalsinghal.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class profilepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);
        Intent intent=getIntent();
        String Firstprofile=intent.getExtras().getString("first_name_user");
        String Lastprofile=intent.getExtras().getString("last_name_user");
        String Dateprofile=intent.getExtras().getString("date_user");
        String Contectprofile=intent.getExtras().getString("contect_user");
        String emailidprofile=intent.getExtras().getString("emailid_user");
        TextView textView,t2,t3,t4,t5;
        textView=(TextView)findViewById(R.id.first_profile);
        textView.setText(Firstprofile);
        t2=(TextView)findViewById(R.id.last_profile);
        t2.setText(Lastprofile);
        t3=(TextView)findViewById(R.id.date_profile);
        t3.setText(Dateprofile);
        t4=(TextView)findViewById(R.id.contect_profile);
        t4.setText(Contectprofile);
        t5=(TextView)findViewById(R.id.email_profile);
        t5.setText(emailidprofile);

    }
}
