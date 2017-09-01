package com.example.mrkunalsinghal.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    database mydb;
   // EditText edit_name,edit_pass;
    EditText edit_first_name,edit_last_name,edit_dob,edit_contect_no,edit_userpassword,edit_userrepassword,edit_username,edit_passwordlogin;
    Button btn_submit;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new database(this);
        edit_contect_no =(EditText)findViewById(R.id.username_id);
        edit_passwordlogin=(EditText)findViewById(R.id.password_id);

         }

    public void loginpage_signup(View view)
    {
        Intent i=new Intent(this,signup.class);
        startActivity(i);
    }
    public void loginpage_doctor(View view)
    {
        Intent i=new Intent(this,doctorsignup.class);
        startActivity(i);
    }

    public void loginpage1(View view)
    {


        Cursor res=mydb.checkdata(edit_contect_no.getText().toString(),edit_passwordlogin.getText().toString());
            //System.out.println("hello "+ res.getCount());
          if(res.moveToNext()){

                String str=res.getString(0);
              String str1=res.getString(3);
              //String str="Kual";
              Intent i=new Intent(this,homepage1.class);
              i.putExtra("name_user",str);
              i.putExtra("contect_nooo",str1);
              startActivity(i);

          }
        else {
              Toast.makeText(MainActivity.this,"invalid username or password",Toast.LENGTH_LONG).show();

          }

    }


}
