package com.example.digitalguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private Button register;
    private EditText userName,password;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        userName = findViewById(R.id.patientName);
        password = findViewById(R.id.addPassword);

        dbHandler = new DBHandler(LoginActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientUserName = userName.getText().toString();
                if(patientUserName.equals("Admin")){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Intent openSetPin = new Intent(LoginActivity.this, PatientProfile.class);
                    openSetPin.putExtra("patient_userName", patientUserName);
                    startActivity(openSetPin);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

}