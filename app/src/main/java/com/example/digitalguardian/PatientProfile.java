package com.example.digitalguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PatientProfile extends AppCompatActivity {
    AlertDialog.Builder builder;
    private DBHandler dbHandler;
    private Patient selectedPatient;
    private EditText name,roomNo,age,bpm,temp,physCondition;
    private String msg;
    private String title;
    private MqttAndroidClient client;
    private static final String SERVER_URI = "tcp://test.mosquitto.org:1883";
    private static final String TAG = "PatientProfile";

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
        roomNo.setText("01");
        //roomNo.setText(""+selectedPatient.getRoomNumber());
        age.setText("80");
        bpm.setText("80");
        //age.setText(""+selectedPatient.getAge());
        //bpm.setText(""+selectedPatient.getBpm());
        temp.setText(""+selectedPatient.getTemperature());
        physCondition.setText(selectedPatient.getDesc());

//        connect();
//
//        client.setCallback(new MqttCallbackExtended() {
//            @Override
//            public void connectComplete(boolean reconnect, String serverURI) {
//                if (reconnect) {
//                    System.out.println("Reconnected to : " + serverURI);
//                    // Re-subscribe as we lost it due to new session
//                    subscribe("iot/pk/rgbvalues");
//                } else {
//                    System.out.println("Connected to: " + serverURI);
//                    subscribe("iot/pk/rgbvalues");
//                }
//            }
//            @Override
//            public void connectionLost(Throwable cause) {
//                System.out.println("The Connection was lost.");
//            }
//            @Override
//            public void messageArrived(String topic, MqttMessage message) throws
//                    Exception {
//                String newMessage = new String(message.getPayload());
//                System.out.println("Incoming message: " + newMessage);
//                if (newMessage.equals(1)) {
//                    title = "Emergency !!";
//                    msg = "Room number 01 Patient has an emergency. Please go to him";
//                }else if(newMessage.equals(2)){
//                    title = "Notification !!";
//                    msg = "Room number 01 Patient needs you now. Please go to him";
//                }else{
//                    msg = "";
//                }
//
//            }
//            @Override
//            public void deliveryComplete(IMqttDeliveryToken token) {
//            }
//        });
//
//        builder = new AlertDialog.Builder(PatientProfile.this);
//        builder.setMessage(msg)
//                .setCancelable(false);
//        //Creating dialog box
//        AlertDialog alert = builder.create();
//
//        alert.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialogInterface) {
//                alert.getWindow().setBackgroundDrawableResource(R.color.red);
//
//
//            }
//        });
//        //Setting the title manually
//        alert.setTitle(title);
//        alert.show();


    }

//    private void connect(){
//        String clientId = MqttClient.generateClientId();
//        client = new MqttAndroidClient(this.getApplicationContext(), SERVER_URI,
//                        clientId);
//        try {
//            IMqttToken token = client.connect();
//            System.out.println(token.getActionCallback());
//            token.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    // We are connected
//                    Log.d(TAG, "onSuccess");
//                    System.out.println(TAG + " Success. Connected to " + SERVER_URI);
//                }
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception)
//                {
//                    // Something went wrong e.g. connection timeout or firewallproblems
//                    Log.d(TAG, "onFailure");
//                    System.out.println(TAG + " Oh no! Failed to connect to " +
//                            SERVER_URI);
//                }
//            });
//        } catch (MqttException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }


//    private void subscribe(String topicToSubscribe) {
//        final String topic = topicToSubscribe;
//        int qos = 1;
//        try {
//            IMqttToken subToken = client.subscribe(topic, qos);
//            subToken.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    System.out.println("Subscription successful to topic: " + topic);
//                }
//                @Override
//                public void onFailure(IMqttToken asyncActionToken,
//                                      Throwable exception) {
//                    System.out.println("Failed to subscribe to topic: " + topic);
//                    // The subscription could not be performed, maybe the user was not
//                    // authorized to subscribe on the specified topic e.g. using wildcards
//                }
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }



}