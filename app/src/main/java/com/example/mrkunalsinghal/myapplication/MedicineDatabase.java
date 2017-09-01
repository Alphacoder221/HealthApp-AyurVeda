package com.example.mrkunalsinghal.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Created by Pavan Chandraval on 7/29/2017.


public class MedicineDatabase extends SQLiteOpenHelper {
public static final String Database="Appointment";
    public static final String table_name="helloworld";

    public static final String madicine_details="madicine_details_t";
    public static final String contect_no="contect_no_t";
    public static final String time="time_t";
    public static final String date="date_t";



public MedicineDatabase(Context context) {
super(context, Database, null, 1);

}

@Override
public void onCreate(SQLiteDatabase db) {

db.execSQL("create table "+ table_name + " (contect_no_t TEXT,madicine_details_t TEXT,time_t TEXT,date_t TEXT);");



}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("drop table if exists "+ table_name);
onCreate(db);
}

public boolean insertData(String contect,String madicine,String t,String d){
SQLiteDatabase db=this.getWritableDatabase();
ContentValues contentValues=new ContentValues();
contentValues.put(contect_no,contect);
contentValues.put(madicine_details,madicine);
contentValues.put(time,t);
contentValues.put(date,d);

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
public Cursor checkdata(String contect)
{
SQLiteDatabase db=this.getWritableDatabase();
Cursor res=db.rawQuery("select * from "+table_name +" where contect_no_t = '"+contect+"'",null);
return  res;
}
}