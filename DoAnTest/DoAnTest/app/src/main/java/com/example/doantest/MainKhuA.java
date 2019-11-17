package com.example.doantest;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainKhuA extends AppCompatActivity implements DialogClassTest.DialogListener {
    private MQTTHelper mqttHelper;
    final String TOPIC = "KhuA/";
    String[] ROOMNAMES = {"A101", "A102", "A103", "A104"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khua);
        final ArrayList<String> roomArray;
        String room;
        final ListView khuAListView = (ListView)findViewById(R.id.khuA);
        //MQTT
        mqttHelper = new MQTTHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                mqttHelper.subscribeToTopic("KhuA/+");
            }

            @Override
            public void connectionLost(Throwable cause) {
                Toast.makeText(MainKhuA.this, "Connect unSuccess!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Toast.makeText(MainKhuA.this, message.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Toast.makeText(MainKhuA.this, "Gửi yêu cầu thành công!", Toast.LENGTH_LONG).show();
            }
        });
        //ListView
        roomArray = new ArrayList<String>();
        for(int i = 0; i < ROOMNAMES.length; i++){
            roomArray.add(ROOMNAMES[i]);
        }
        final ArrayAdapter roomAdapter = new ArrayAdapter(MainKhuA.this, android.R.layout.simple_list_item_1, roomArray);
        khuAListView.setAdapter(roomAdapter);

        khuAListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogClassTest dialogClassTest = new DialogClassTest();
                Bundle bundle = new Bundle();
                String room = roomArray.get(position);
                String block = TOPIC;
                bundle.putString("ROOMID",room);
                bundle.putString("BLOCK",block);
                dialogClassTest.setArguments(bundle);
                dialogClassTest.show(getSupportFragmentManager(), "ROOMID");
            }
        });
    }

    @Override
    public void sendMess(String roomID, String mess, boolean flag) {
        if( flag == true) {
            mqttHelper.publishToTopic("KhuA/" + roomID, mess);
        }
        else  {
            Toast.makeText(MainKhuA.this, "Bạn chưa chọn Tiết tắt thiết bị! Vui lòng chọn lại", Toast.LENGTH_LONG).show();
        }
    }
}
