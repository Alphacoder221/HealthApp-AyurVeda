package com.example.mrkunalsinghal.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Mr kunal singhal on 30-Jul-17.
 */
public class appointmentdb extends SQLiteOpenHelper {
    public static final String Database = "appointmentdatabase";
    public static final String table_name = "appointment_table";
    //public static final String name_t="name_t";
    public static final String user_name="user_name_t";
    public static final String disease_user="disease_t";
    public static final String hospital_name="hospital_name_t";
    public static final String doctor_name="doctor_name_t";
    public static final String user_contect_no="user_contect_no_t";




    public appointmentdb(Context context) {
        super(context, Database, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table "+ table_name + " (first_name_t TEXT,last_name_t TEXT,);" );
       // db.execSQL("create table " + table_name + " (doctor_name_t TEXT,hospital_name_t TEXT,doctor_DOB_t TEXT,doctor_contect_no_t TEXT,doctor_email_t TEXT,doctor_detail_t TEXT);");
        db.execSQL("create table "+ table_name + " (user_name_t TEXT,disease_t TEXT,hospital_name_t TEXT,doctor_name_t TEXT,user_contect_no_t TEXT);" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ table_name);
        onCreate(db);

    }
    public Cursor viewdata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name,null);
        return  res;

    }

    public boolean insertData(String username,String diseaseuser,String hospitalname,String doctorname,String usercontectno){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(user_name,username);
        contentValues.put(disease_user,diseaseuser);
        contentValues.put(hospital_name,hospitalname);
        contentValues.put(doctor_name,doctorname);
        contentValues.put(user_contect_no,usercontectno);

        long res=db.insert(table_name,null,contentValues);
        if(res==-1)

            return false;
        else {
            return true;
        }

    }





}