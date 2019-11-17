package com.example.doantest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {
    MQTTHelper mqttHelper;
    TextView dataReceived;
    ImageView khuA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        dataReceived = (TextView) findViewById(R.id.schoolLabel);
        khuA = (ImageView) findViewById(R.id.khuA);

        //Init MQTT
        mqttHelper = new MQTTHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Toast.makeText(MainActivity.this, "Connect Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void connectionLost(Throwable throwable) {
                Toast.makeText(MainActivity.this, "Connect unSuccess", Toast.LENGTH_LONG).show();
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug", mqttMessage.toString());
                //Tách tên topic ex: khuA/A101 -> A101
                String[] topicName = topic.split("/",2);
                //Tách các thiết bị
                String[] deviceValue = mqttMessage.toString().split(" ", 10);
                dataReceived.setText(topicName[1]);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });

        //Test image
        khuA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mqttHelper.publishToTopic("khuA/A101", "AC:ON LAMP:ON");
//                Toast.makeText(MainActivity.this, "Publish Success", Toast.LENGTH_LONG).show();
                openKhuAActivity();
            }
        });
    }

    public void openKhuAActivity() {
        Intent intent = new Intent(this, MainKhuA.class);
        startActivity(intent);
    }
}
