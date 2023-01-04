package com.example.digitalguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

//import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private MqttAndroidClient client;
    private static final String SERVER_URI = "tcp://test.mosquitto.org:1883";
    private static final String TAG = "MainActivity";
    private List<Patient> patientList;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHandler = new DBHandler(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientList = dbHandler.getAllUser();

//        patientList.add(new Patient("12341234","Sweda Larsson",01,31,"this is test","Female", R.drawable.old_women_1,98));
//        patientList.add(new Patient("56782345","Mark Karlsson",02,34,"this is test", "Male", R.drawable.old_man_1,91));
//        patientList.add(new Patient("90126745","Lucas Jonsson",03,37,"this is test","Male", R.drawable.old_man_2,92));
//        patientList.add(new Patient("12341234","Sweda Larsson",01,31,"this is test","Female", R.drawable.old_women_1,101));
//        patientList.add(new Patient("56782345","Mark Karlsson",02,34,"this is test", "Male", R.drawable.old_man_1,90));
//        patientList.add(new Patient("90126745","Lucas Jonsson",03,37,"this is test","Male", R.drawable.old_man_2,109));
//        System.out.println(patientList);

        //setting adapter and listview
        PatientAdapter adapter = new PatientAdapter(getApplicationContext(),
                R.layout.activity_listview, patientList);
        ListView listview = findViewById(R.id.patient_list);
        listview.setAdapter(adapter);
        listview.setScrollContainer(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(i);
                System.out.println(l);
                Intent openSetPin = new Intent(MainActivity.this, PatientProfile.class);
                openSetPin.putExtra("patient_id", adapter.getItem(i).getIndex());
                startActivity(openSetPin);
            }
        });
        //connect();


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
//
//                 /* add code here to interact with elements
//                 (text views, buttons)
//                 using data from newMessage
//                 */
//
//
//            }
//            @Override
//            public void deliveryComplete(IMqttDeliveryToken token) {
//            }
//        });

    }

//    private void connect(){
//        String clientId = MqttClient.generateClientId();
//        client =
//                new MqttAndroidClient(this.getApplicationContext(), SERVER_URI,
//                        clientId);
//        try {
//            IMqttToken token = client.connect();
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
//            System.out.println(token.getActionCallback());
//            token.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    // We are connected
//                    Log.d(TAG, "onSuccess");
//                    System.out.println(TAG + " Success. Connected to " + SERVER_URI);
//                    System.out.println("@@@@@@@@@@@@@@@");
//                }
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception)
//                {
//                    // Something went wrong e.g. connection timeout or firewallproblems
//                    Log.d(TAG, "onFailure");
//                    System.out.println(TAG + " Oh no! Failed to connect to " +
//                            SERVER_URI);
//                    System.out.println("%%%%%%%%%%%%%%%%%%%");
//                }
//            });
//        } catch (MqttException e) {
//            System.out.println("**********************");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//
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