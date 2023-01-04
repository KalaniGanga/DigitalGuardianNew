package com.example.digitalguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class PatientProfile extends AppCompatActivity {
    AlertDialog.Builder builder;
    private DBHandler dbHandler;
    private Patient selectedPatient;
    private EditText name,roomNo,age,bpm,temp,physCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new DBHandler(PatientProfile.this);
        setContentView(R.layout.activity_patient_profile);

        // Getting the intent which started this activity
        Intent intent = getIntent();
        // Get the data of the activity providing the same key value
        String patientId = intent.getStringExtra("patient_id");
        String userName = intent.getStringExtra("patient_userName");

        if(patientId == null || patientId == ""){
            selectedPatient = dbHandler.getPatientDetailsByUsername(userName);
        }else {
            selectedPatient = dbHandler.getPatientDetails(patientId);
        }
        name = findViewById(R.id.name);
        roomNo = findViewById(R.id.roomNo);
        age = findViewById(R.id.patientAge);
        bpm = findViewById(R.id.bpmDisplay);
        temp = findViewById(R.id.tempDisplay);
        physCondition = findViewById(R.id.phycondition);

        name.setText(selectedPatient.getName());
        roomNo.setText(""+selectedPatient.getRoomNumber());
        age.setText(""+selectedPatient.getAge());
        bpm.setText(""+selectedPatient.getBpm());
        temp.setText(""+selectedPatient.getTemperature());
        physCondition.setText(selectedPatient.getDesc());

        builder = new AlertDialog.Builder(PatientProfile.this);
        builder.setMessage("Room number 01 Patient needs you now. Please go to him")
                .setCancelable(false);
        //Creating dialog box
        AlertDialog alert = builder.create();

        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alert.getWindow().setBackgroundDrawableResource(R.color.green);


            }
        });
        //Setting the title manually
        alert.setTitle("Notification !!");
        alert.show();


    }
}