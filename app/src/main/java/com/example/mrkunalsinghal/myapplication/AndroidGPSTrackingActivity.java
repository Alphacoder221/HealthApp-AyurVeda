package com.example.mrkunalsinghal.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class AndroidGPSTrackingActivity extends Activity {

    Context context=null;
    Button btnShowLocation;
    Geocoder geocoder;

    // GPSTracker class
    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage1);



        btnShowLocation = (Button) findViewById(R.id.emergency);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                gps = new GPSTracker(AndroidGPSTrackingActivity.this);


                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Log.i("Latitude",""+latitude);
                    Log.i("Longitude",""+longitude);

                    String string="lovely Professional University,Phagwara,Punjab ";

                    String no="8559075539";

                    Intent intent=new Intent(getApplicationContext(),AndroidGPSTrackingActivity.class);
                    PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                    ActivityCompat.requestPermissions(AndroidGPSTrackingActivity.this,new String[]{Manifest.permission.SEND_SMS},1);

                    if(!no.isEmpty()&&!string.isEmpty())
                    {
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage(no,null,string,pi,null);
                        Toast.makeText(getApplicationContext(), "Message Sent successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Some Problem!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                    }
                  //  Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                   // Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{

                    gps.showSettingsAlert();
                }

            }
        });
    }

}

