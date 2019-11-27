package com.example.doantest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class RfidActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button blockA, blockB, blockC, sendBtn;
    private TextView roomchoose;
    private String block, blockS, room;
    MQTTHelper mqttHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfid);
        blockA = (Button) findViewById(R.id.blockA);
        blockB = (Button) findViewById(R.id.blockB);
        blockC = (Button) findViewById(R.id.blockC);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        roomchoose = (TextView) findViewById(R.id.roomChoose);
        final Spinner spinner1 = findViewById(R.id.spinner1);
        final ArrayAdapter<CharSequence> adapterA = ArrayAdapter.createFromResource(this,
                R.array.roomA, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> adapterB = ArrayAdapter.createFromResource(this,
                R.array.roomB, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> adapterC = ArrayAdapter.createFromResource(this,
                R.array.roomC, android.R.layout.simple_spinner_item);

        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapterA);
        spinner1.setOnItemSelectedListener(this);
        mqttHelper = new MQTTHelper(getApplicationContext());
        block = "Khu A";
        blockS = "KhuA";
        blockA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner1.setAdapter(adapterA);
                block = "Khu A";
                blockS = "KhuA";
            }
        });

        blockB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner1.setAdapter(adapterB);
                block = "Khu B";
                blockS = "KhuB";
            }
        });

        blockC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner1.setAdapter(adapterC);
                block = "Khu C";
                blockS = "KhuC";
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttHelper.publishToTopic(blockS+ "/" + room, "RFID");
            }
        });

        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Toast.makeText(RfidActivity.this, "Gửi yêu cầu thành công! Kiểm tra màn hình HMI.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        room = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        roomchoose.setText("Bạn chọn: " + block + "/" +room);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
