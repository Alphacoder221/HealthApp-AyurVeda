package com.example.mrkunalsinghal.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mr kunal singhal on 25-Jul-17.
 */
public class database extends SQLiteOpenHelper {
    public static final String Database="userprofiled";
    public static final String table_name="userprofile";
    //public static final String name_t="name_t";
    public static final String repassword_t="repassword_t";
    public static final String password_t="password_t";
    public static final String first_name="first_name_t";
    public static final String last_name="last_name_t";
    public static final String DOB="DOB_t";
    public static final String contect_no="contect_no_t";
    public static final String emailid="email_t";

    //public static final String email_id="email_id_t";

    public database(Context context) {
        super(context, Database, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table "+ table_name + " (first_name_t TEXT,last_name_t TEXT,);" );
        db.execSQL("create table "+ table_name + " (first_name_t TEXT,last_name_t TEXT,DOB_t TEXT,contect_no_t TEXT,password_t TEXT,repassword_t TEXT,email_t TEXT);" );



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ table_name);
        onCreate(db);

    }
    public boolean insertData(String firstname,String lastname,String date,String contectno,String userpassword,String userrepassword,String useremail){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(first_name,firstname);
        contentValues.put(last_name,lastname);
        contentValues.put(DOB,date);
        contentValues.put(contect_no,contectno);
        contentValues.put(password_t,userpassword);
        contentValues.put(repassword_t,userrepassword);
        contentValues.put(emailid,useremail);
        long res=db.insert(table_name,null,contentValues);
        if(res==-1)

            return false;
        else {
            return true;
        }

    }
public Cursor viewdata()
{
    SQLiteDatabase db=this.getWritableDatabase();
    Cursor res=db.rawQuery("select * from "+table_name,null);
    return  res;

}
    public Cursor checkdata(String ussername_login,String pass_login)
    {
        SQLiteDatabase db=this.getWritableDatabase();


        Cursor res=db.rawQuery("select * from "+table_name +" where contect_no_t = '"+ ussername_login +"' AND password_t = '" + pass_login+"'",null  );
        return  res;
    }

    public Cursor user_data(String contectnoo)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name+ " where contect_no_t = "+"'"+contectnoo+"'",null);
        return  res;

    }


    public static class setappointment extends Activity implements AdapterView.OnItemSelectedListener {
        Spinner diseasedropdown,hospitaldropdown,doctordropdown;
        doctordatabase doctordb;
        appointmentdb appointdb;
        String username_login,contectname_login;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_setappointment);
            doctordb=new doctordatabase(this);
            appointdb=new appointmentdb(this);
            diseasedropdown = (Spinner)findViewById(R.id.spinner1);
            hospitaldropdown=(Spinner)findViewById(R.id.spinner2);
            doctordropdown=(Spinner)findViewById(R.id.spinner3);
            Intent intent=getIntent();
            username_login=intent.getExtras().getString("username");
            contectname_login=intent.getExtras().getString("contectuser");


            Cursor res1=doctordb.viewdiseasedata();
            ArrayList<String> options=new ArrayList<String>();

            res1.moveToFirst();

            while (res1.isAfterLast() == false)
            {
                options.add(res1.getString(0).toString());
                res1.moveToNext();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, options);

            diseasedropdown.setAdapter(adapter);


            Cursor res2=doctordb.viewhospitaldata();
            ArrayList<String> options2=new ArrayList<String>();

            res2.moveToFirst();

            while (res2.isAfterLast() == false)
            {
                options2.add(res2.getString(0).toString());
                res2.moveToNext();
            }
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, options2);

            hospitaldropdown.setAdapter(adapter1);



            Cursor res3=doctordb.viewdoctordata();
            ArrayList<String> options3=new ArrayList<String>();

            res3.moveToFirst();

            while (res3.isAfterLast() == false)
            {
                options3.add(res3.getString(0).toString());
                res3.moveToNext();
            }
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, options3);

            doctordropdown.setAdapter(adapter2);
            diseasedropdown.setOnItemSelectedListener(this);



         //   hospitaldropdown.setOnItemSelectedListener(this);

        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String itemm=parent.getItemAtPosition(position).toString();
            //  String hospital_data=parent.getItemAtPosition(position).toString();
            Cursor res=doctordb.viewhospitaldropdata(itemm);
            ArrayList<String> optionshospital=new ArrayList<String>();

            res.moveToFirst();
            while (res.isAfterLast() == false)
            {
                optionshospital.add(res.getString(0).toString());
                res.moveToNext();
            }

    // use default spinner item to show options in spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,optionshospital);
            hospitaldropdown.setAdapter(adapter);





          /* Cursor res1=doctordb.viewdoctordropdata(itemm,hospital_data);
            ArrayList<String> optionsdoctor=new ArrayList<String>();

            res1.moveToFirst();
            while (res1.isAfterLast() == false)
            {
                optionsdoctor.add(res1.getString(0).toString());
                res1.moveToNext();
            }
    // use default spinner item to show options in spinner
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,optionsdoctor);
            doctordropdown.setAdapter(adapter1);
            */
    /*
            //hospitaldropdown.setAdapter(adapter);
           // Toast.makeText(parent.getContext(),""+itemm,Toast.LENGTH_LONG).show();
    */
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
        public void show_doctor_detail(View view)
        {
            String str=doctordropdown.getItemAtPosition(doctordropdown.getSelectedItemPosition()).toString();
            Cursor res=doctordb.showdoctordetails(str);
            if(res.getCount()==0){
                Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("Doctor Name    :  "+ res.getString(0)+"\n");
                    buffer.append("Hospital Name  :  "+res.getString(1)+"\n");
                    buffer.append("Dob            :  "+res.getString(2)+"\n");
                    buffer.append("Contect No.    :  "+res.getString(3)+"\n");
                    buffer.append("emailid        :  "+res.getString(4)+"\n");
                    buffer.append("Details        :  "+res.getString(5)+"\n");

                }
                showMessage("Data",buffer.toString());
            }
            int a=view.getId();


        }

        public void showMessage(String title,String message){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.show();
        }
        public void make_appointment(View view)
        {
            boolean bool;
            // Toast.makeText(this, "kunal", Toast.LENGTH_LONG).show();
            String diseasenamee =diseasedropdown.getItemAtPosition(diseasedropdown.getSelectedItemPosition()).toString();
            String doctornamee =doctordropdown.getItemAtPosition(doctordropdown.getSelectedItemPosition()).toString();
            String hospitalnamee =hospitaldropdown.getItemAtPosition(hospitaldropdown.getSelectedItemPosition()).toString();

            //if (edit_userpassword.getText() == edit_userrepassword.getText() ) {
            bool = appointdb.insertData( username_login,diseasenamee,hospitalnamee,doctornamee,contectname_login);
            // Toast.makeText(this, "kunal", Toast.LENGTH_SHORT).show();
            Cursor res2=doctordb.doctornumber(hospitalnamee,doctornamee);
            res2.moveToNext();
            String no= res2.getString(0);

            String message="make appointment by "+username_login + "\n  Contect no = " + contectname_login ;
           /* if (bool == true)
                Toast.makeText(setappointment.this, "done", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(setappointment.this, "xyz", Toast.LENGTH_LONG).show();
    */


            //String no=contectname_login;
            Intent intent=new Intent(getApplicationContext(),setappointment.class);
            PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

            ActivityCompat.requestPermissions(setappointment.this,new String[]{Manifest.permission.SEND_SMS},1);

            if(!no.isEmpty()&&!message.isEmpty())
            {
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(no,null,message,pi,null);
                Toast.makeText(getApplicationContext(), "Message Sent successfully!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Some Problem!!!!!!!!!!", Toast.LENGTH_SHORT).show();
            }
        }
        public void show_appointment(View view)
        {

            Cursor res=appointdb.viewdata();
            if(res.getCount()==0){
                Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("User Name      :  "+ res.getString(0)+"\n");
                    buffer.append("Disease Name   :  "+ res.getString(1)+"\n");
                    buffer.append("Hospital Name  :  "+res.getString(2)+"\n");
                    buffer.append("Doctor Name    :  "+ res.getString(3)+"\n");
                    buffer.append("User Contect   :  "+res.getString(4)+"\n");
                }
                showMessage("Data",buffer.toString());
            }
            int a=view.getId();


        }
        public void showMessage2(String title,String message){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.show();
        }


    }
}
