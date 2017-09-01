package com.example.mrkunalsinghal.myapplication;


import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainMedicine extends AppCompatActivity {

    MedicineDatabase MedicineDatabase;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_medicine);


        MedicineDatabase =new MedicineDatabase(this);



        TextView t1=(TextView)findViewById(R.id.ttt);
        Cursor cursor= MedicineDatabase.viewdata();
        String s="";


        Calendar  calendar=Calendar.getInstance();

        s="";
        int currenttime=calendar.get(Calendar.HOUR_OF_DAY);
        while(cursor.moveToNext())
        {

            int x=Integer.parseInt(cursor.getString(3));


            if(x>=currenttime) {
                s = s + cursor.getString(0);
                s += "    ";
                s = s + cursor.getString(1);
                s += "    ";
                s = s + cursor.getString(2);
                s += "    ";
                s = s + cursor.getString(3);
                s += "\n\n";
            }
        }

        if(s.equals(""))
        {
            s ="Please Add New Schedule";
        }
        else
        {
            String k="PHONE NUMBER  DISEASE   TIME    DATE\n\n";
            k=k+s;
            s=k;
        }
        t1.setText(s);

        String contact="8559075539";

        cursor= MedicineDatabase.checkdata(contact);
        calendar=Calendar.getInstance();
        int x=calendar.get(calendar.HOUR_OF_DAY);
        int m=calendar.get(calendar.MINUTE);
        boolean b=true;
        int timeofsystem=x*60+m;


        while(cursor.moveToNext())
        {

               Log.i("Hello",""+currenttime);

            s=cursor.getString(2);
            int y=Integer.parseInt(s);
            y=y*60;


            Log.i("hyyyyyee",""+y);

            if((y-timeofsystem)<=120&&(y-timeofsystem)>0&&b){
                b=false;

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
                notificationIntent.addCategory("android.intent.category.DEFAULT");
                PendingIntent broadcast = PendingIntent.getBroadcast(MainMedicine.this,50, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND,2);
                //(y-timeofsystem)*60*1000
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
            }
        }
        Button fabb = (Button) findViewById(R.id.fab);
        fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainMedicine.this,AddMadicineDetail.class);
                startActivity(intent);
            }
        });
    }

}
