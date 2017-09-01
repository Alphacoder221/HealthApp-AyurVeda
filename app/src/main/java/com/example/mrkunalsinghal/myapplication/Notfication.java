package com.example.mrkunalsinghal.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Calendar;

public class Notfication extends AppCompatActivity {

    MedicineDatabase MedicineDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfication);

        TextView textView=(TextView)findViewById(R.id.infoTextView);

        MedicineDatabase = new MedicineDatabase(this);
        String S="8528221599";
        Cursor cursor= MedicineDatabase.checkdata(S);
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);

         String val="DISEASE   TIME    DATE\n\n";
        while(cursor.moveToNext())
        {
            int y=Integer.parseInt(cursor.getString(2));
            if(y-hour<=2&&y>=hour)
            {
                val=val+cursor.getString(1);
                val=val+"    ";
                val=val+cursor.getString(2);
                val=val+"    ";
                val=val+cursor.getString(3);
                val=val+"    ";
                val=val+"\n\n";
            }
        }
        textView.setText(val);
    }
}
