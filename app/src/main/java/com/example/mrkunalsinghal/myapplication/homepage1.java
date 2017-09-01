package com.example.mrkunalsinghal.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class homepage1 extends AppCompatActivity {
    ViewPager viewPager;
    database mydb;
    GPSTracker gps;

     Button   startButton;
    // EditText edit_name,edit_pass;
    EditText edit_first_name,edit_last_name,edit_dob,edit_contect_no,edit_userpassword,edit_userrepassword,edit_username,edit_passwordlogin;
    Button btn_submit;
    Button btn;
    String nameee,contect_n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage1);


        mydb=new database(this);
        gps=new GPSTracker(this);
        edit_contect_no =(EditText)findViewById(R.id.username_id);
        edit_passwordlogin=(EditText)findViewById(R.id.password_id);
        startButton=(Button)findViewById(R.id.medicieCall);




        viewPager =(ViewPager)findViewById(R.id.viewPager);
        ViewPageAdapter viewpageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewpageAdapter);
          Intent intent=getIntent();
         nameee=intent.getExtras().getString("name_user");
        contect_n=intent.getExtras().getString("contect_nooo");
        TextView textView;
        textView=(TextView)findViewById(R.id.usertitle);
        textView.setText(nameee);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(homepage1.this,MainMedicine.class);
                startActivity(i);
            }
        });

    }
    public void user_appointment(View view)
    {
        Intent i=new Intent(this,database.setappointment.class);
        i.putExtra("contectuser",contect_n);
        i.putExtra("username",nameee);
        startActivity(i);
    }


    public void view_appointment(View view)
    {
        Intent i=new Intent(this,showappointment.class);
        i.putExtra("contectuser",contect_n);
        i.putExtra("username",nameee);
        startActivity(i);
    }


    public void user_profile(View view)
    {
         Cursor res=mydb.user_data(contect_n);
        //System.out.println("hello "+ res.getCount());
        if(res.moveToNext()){

            String first_name=res.getString(0);
            String last_name=res.getString(1);
            String date=res.getString(2);
            String contect_no=res.getString(3);
            String emailid_id=res.getString(6);

            //String str="Kual";
            Intent i=new Intent(this,profilepage.class);
            i.putExtra("first_name_user",first_name);
            i.putExtra("last_name_user",last_name);
            i.putExtra("date_user",date);
            i.putExtra("contect_user",contect_no);
            i.putExtra("emailid_user",emailid_id);
            startActivity(i);

        }
        else {
            Toast.makeText(homepage1.this,"invalid username or password",Toast.LENGTH_LONG).show();

        }

    }

    public void useremergency(View view){

       // Log.i("Latitude","hello");

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Log.i("Latitude",""+latitude);
            Log.i("Longitude",""+longitude);

            String address=" Lovely Professional University  "+latitude+"  "+longitude+"";


            String no="8528221599";

            Intent intent=new Intent(getApplicationContext(),homepage1.class);
            PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(no,null,address,pi,null);

            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{

            gps.showSettingsAlert();
        }

    }

}
