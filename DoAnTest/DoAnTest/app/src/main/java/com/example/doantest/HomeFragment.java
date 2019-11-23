package com.example.doantest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class HomeFragment extends Fragment {
    MQTTHelper mqttHelper;
    TextView dataReceived;
    ImageView khuA;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        //Ánh xạ
        dataReceived = (TextView) view.findViewById(R.id.schoolLabel);
        khuA = (ImageView) view.findViewById(R.id.khuA);
        //Test image
        khuA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mqttHelper.publishToTopic("khuA/A101", "AC:ON LAMP:ON");
//                Toast.makeText(MainActivity.this, "Publish Success", Toast.LENGTH_LONG).show();
                openKhuAActivity();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void openKhuAActivity() {
        Intent intent = new Intent(getActivity(), MainKhuA.class);
        startActivity(intent);
    }
}
