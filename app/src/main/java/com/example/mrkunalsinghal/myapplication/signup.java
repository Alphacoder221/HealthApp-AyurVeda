package com.example.mrkunalsinghal.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    database mydb;
    // EditText edit_name,edit_pass;
    EditText edit_first_name,edit_last_name,edit_dob,edit_contect_no,edit_userpassword,edit_userrepassword,edit_emailid;
    Button btn_submit;
   // Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mydb=new database(this);
        //edit_name=(EditText)findViewById(R.id.nameid);
        //edit_pass=(EditText)findViewById(R.id.passwordid);
        edit_first_name=(EditText)findViewById(R.id.firstname_id);
        edit_last_name=(EditText)findViewById(R.id.lastname_id);
        edit_dob=(EditText)findViewById(R.id.dob_id);
        edit_contect_no=(EditText)findViewById(R.id.contactnumber_id);
        edit_userpassword=(EditText)findViewById(R.id.passwordsign_id);
        edit_userrepassword=(EditText)findViewById(R.id.confirmpasswordsign_id);
        edit_emailid=(EditText)findViewById(R.id.email_id);

        //btn=(Button)findViewById(R.id.btnid);
        btn_submit=(Button)findViewById(R.id.submit_id);

    }


    public void dothis_showxyz(View view)
    {

        Cursor res=mydb.viewdata();
        if(res.getCount()==0){
            Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append("firstname "+ res.getString(0)+"\n");
                buffer.append("last name "+res.getString(1)+"\n");
                buffer.append("Dob"+res.getString(2)+"\n");
                buffer.append("contect no."+res.getString(3)+"\n");
                buffer.append("password"+res.getString(4)+"\n");
                buffer.append("repassword"+res.getString(5)+"\n");
                buffer.append("emailid"+res.getString(6)+"\n");


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

    public void dothis_submitxyz(View view) {
        boolean bool;
        Toast.makeText(this, "kunal", Toast.LENGTH_LONG).show();
        //if (edit_userpassword.getText() == edit_userrepassword.getText() ) {
        bool = mydb.insertData(edit_first_name.getText().toString(), edit_last_name.getText().toString(), edit_dob.getText().toString(),
                edit_contect_no.getText().toString(), edit_userpassword.getText().toString(), edit_userrepassword.getText().toString(), edit_emailid.getText().toString());
        // Toast.makeText(this, "kunal", Toast.LENGTH_SHORT).show();


        if (bool == true) {
            Toast.makeText(signup.this, "done", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            }
        }
       // else
            //Toast.makeText(this, "please enter same password", Toast.LENGTH_SHORT).show();
    //}

}
