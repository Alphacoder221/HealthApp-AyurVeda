package com.example.mrkunalsinghal.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMadicineDetail extends AppCompatActivity {
    MedicineDatabase MedicineDatabase;

    EditText madicinedetail,numberoftime,numberofdays;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_madicine_detail);
        MedicineDatabase =new MedicineDatabase(this);

        Button  button=(Button)findViewById(R.id.addMedicine);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                madicinedetail=(EditText)findViewById(R.id.editText);
                numberofdays=(EditText)findViewById(R.id.editText3);
                numberoftime=(EditText)findViewById(R.id.editText2);
                String contact="8559075539";

                String madicinedetail_data=madicinedetail.getText().toString();

                String numberoftime_data=numberoftime.getText().toString();

                int times=Integer.parseInt(numberoftime_data);

                times=times-1;
                int t=12/times;

                boolean b=false;
                    int currenttime=8;
                    for(int j=0;j<=times;j++) {
                  b= MedicineDatabase.insertData(contact, madicinedetail_data, "" + currenttime,"30");
                        currenttime=currenttime+t;
                    }
                if(b)
                {
                    Toast.makeText(AddMadicineDetail.this,"Succesfully inserted",Toast.LENGTH_LONG).show();
                }


                Intent intent=new Intent(AddMadicineDetail.this,MainMedicine.class);
                startActivity(intent);


            }
        });


    }
}
