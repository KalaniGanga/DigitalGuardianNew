package com.example.digitalguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button signUp;
    private EditText userName,patientId,password;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUp = findViewById(R.id.button);
        userName = findViewById(R.id.name);
        patientId = findViewById(R.id.user_id);
        password = findViewById(R.id.password);

        dbHandler = new DBHandler(RegisterActivity.this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Patient patient = new Patient();
                patient.setUserName(userName.getText().toString());
                patient.setIndex(patientId.getText().toString());
                patient.setPassword(password.getText().toString());

                // validating if the text fields are empty or not.
//                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewPatient("poornima","test");
                dbHandler.updatePatient(patient);

                // after adding the data we are displaying a toast message.
                Toast.makeText(RegisterActivity.this, "Patient has been added.", Toast.LENGTH_SHORT).show();
                userName.setText("");
                patientId.setText("");
                password.setText("");
//                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }
}