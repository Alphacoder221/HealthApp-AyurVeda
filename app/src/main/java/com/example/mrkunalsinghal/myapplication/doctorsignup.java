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

public class doctorsignup extends AppCompatActivity {

    doctordatabase mad;
    // EditText edit_name,edit_pass;
    EditText edit_first_name,edit_last_name,edit_dob,edit_contect_no,edit_userpassword,edit_userrepassword,edit_emailid;
    Button btn_submit;
    EditText edit_doctor_name,edit_hospital_name,edit_doctor_dob,edit_doctor_contect,edit_doctor_email,edit_doctor_detail;
    // Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorsignup);
        mad =new doctordatabase(this);
        //edit_name=(EditText)findViewById(R.id.nameid);
        //edit_pass=(EditText)findViewById(R.id.passwordid);
        edit_doctor_name=(EditText)findViewById(R.id.doctor_name_id);
        edit_hospital_name=(EditText)findViewById(R.id.hospital_id);
        edit_doctor_dob=(EditText)findViewById(R.id.dobdoctor_id);
        edit_doctor_contect=(EditText)findViewById(R.id.contactnumberdoctor_id);
        edit_doctor_email=(EditText)findViewById(R.id.doctor_email_id);
        edit_doctor_detail=(EditText)findViewById(R.id.detailsdoctor_id);


        //btn=(Button)findViewById(R.id.btnid);
        btn_submit=(Button)findViewById(R.id.doctor_submit_id);

    }


    public void dothis_show_doctor(View view)
    {

        Cursor res= mad.viewdata();
        if(res.getCount()==0){
            Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append("name "+ res.getString(0)+"\n");
                buffer.append("hospital  "+res.getString(1)+"\n");
                buffer.append("Dob"+res.getString(2)+"\n");
                buffer.append("contect no."+res.getString(3)+"\n");
                buffer.append("email id"+res.getString(4)+"\n");
                buffer.append("details"+res.getString(5)+"\n");

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

    public void dothis_submit_doctor(View view) {
        boolean bool;
        //if (edit_userpassword.getText() == edit_userrepassword.getText() ) {
        bool = mad.insertData(edit_doctor_name.getText().toString(),edit_hospital_name.getText().toString(),edit_doctor_dob.getText().toString(),edit_doctor_contect.getText().toString(),edit_doctor_email.getText().toString(),edit_doctor_detail.getText().toString());
        // Toast.makeText(this, "kunal", Toast.LENGTH_SHORT).show();

        if (bool == true)
            Toast.makeText(doctorsignup.this, "done", Toast.LENGTH_LONG).show();
            Intent iii = new Intent(this,MainActivity.class);
    }
    // else
    //Toast.makeText(this, "please enter same password", Toast.LENGTH_SHORT).show();
    //}

}
