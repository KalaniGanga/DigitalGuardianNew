package com.example.digitalguardian;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PatientRegisterActivity extends AppCompatActivity {
    private Button register;
    private EditText userName,gender,age,email,phone,bpm,temp,desc,roomNo;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        register = findViewById(R.id.button);
        userName = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        bpm = findViewById(R.id.bpm);
        temp = findViewById(R.id.temp);
        desc = findViewById(R.id.desc);
        roomNo = findViewById(R.id.roomNo);

        dbHandler = new DBHandler(PatientRegisterActivity.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Patient patient = new Patient();
                patient.setName(userName.getText().toString());
                patient.setGender(gender.getText().toString());
                patient.setAge(Integer.parseInt(age.getText().toString()));
                patient.setEmail(email.getText().toString());
                patient.setPhone(phone.getText().toString());
                patient.setBpm(Integer.parseInt(bpm.getText().toString()));
                patient.setTemperature(Integer.parseInt(temp.getText().toString()));
                patient.setDesc(desc.getText().toString());
                patient.setRoomNumber(Integer.parseInt(roomNo.getText().toString()));

                // validating if the text fields are empty or not.
//                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                // on below line we are calling a method to add new patient to db
                dbHandler.addNewPatient(patient);

                // after adding the data we are displaying a toast message.
                Toast.makeText(PatientRegisterActivity.this, "Patient has been added.", Toast.LENGTH_SHORT).show();
                userName.setText("");
                gender.setText("");
                age.setText("");
                email.setText("");
                phone.setText("");
                bpm.setText("");
                temp.setText("");
                desc.setText("");
                roomNo.setText("");
            }
        });
    }
}