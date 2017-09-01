package com.example.mrkunalsinghal.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class doctordatabase extends SQLiteOpenHelper {
    public static final String Database="doctorprofiled";
    public static final String table_name="docprofile";
    //public static final String name_t="name_t";
    public static final String dotor_name="doctor_name_t";
    public static final String hospital_name="hospital_name_t";
    public static final String doctor_dob="doctor_DOB_t";
    public static final String doctor_contect_no="doctor_contect_no_t";
    public static final String doctor_email="doctor_email_t";
    public static final String doctor_detail="doctor_detail_t";
    //public static final String email_id="email_id_t";

    public doctordatabase(Context context) {
        super(context, Database, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table "+ table_name + " (first_name_t TEXT,last_name_t TEXT,);" );
        db.execSQL("create table "+ table_name + " (doctor_name_t TEXT,hospital_name_t TEXT,doctor_DOB_t TEXT,doctor_contect_no_t TEXT,doctor_email_t TEXT,doctor_detail_t TEXT);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ table_name);
        onCreate(db);

    }
    public boolean insertData(String doctorname,String hospitalname,String date,String contectno,String doctoremail,String details){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(dotor_name,doctorname);
        contentValues.put(hospital_name,hospitalname);
        contentValues.put(doctor_dob,date);
        contentValues.put(doctor_contect_no,contectno);
        contentValues.put(doctor_email,doctoremail);
        contentValues.put(doctor_detail,details);
        long res=db.insert(table_name,null,contentValues);
        if(res==-1)

            return false;
        else {
            return true;
        }

    }
    public  Cursor viewdiseasedata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select DISTINCT doctor_detail_t from "+table_name,null);
        return  res;

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
    public Cursor viewhospitaldata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select DISTINCT hospital_name_t from "+table_name,null);
        return  res;
    }
    public Cursor viewdoctordata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select DISTINCT doctor_name_t from "+table_name,null);
        return  res;
    }
    public  Cursor viewhospitaldropdata(String disease)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select DISTINCT hospital_name_t from "+table_name+ " where doctor_detail_t = '"+ disease +"'",null);
        return  res;

    }
    public Cursor viewdoctordropdata(String hospitall)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select DISTINCT doctor_name_t from "+table_name+ " where hospital_name_t = '"+hospitall+"'" ,null);
        return  res;
    }
    public Cursor showdoctordetails(String doctor_name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name+ " where doctor_name_t = "+"'"+doctor_name+"'",null);
        return  res;
    }
    public Cursor doctornumber(String hospitalnamee,String doctornamee){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select doctor_contect_no_t from "+table_name+ " where doctor_name_t = "+"'"+doctornamee+"'"+" AND hospital_name_t = '"+ hospitalnamee+"'" ,null);
        return  res;
    }










}
