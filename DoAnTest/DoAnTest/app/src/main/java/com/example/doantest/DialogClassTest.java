package com.example.doantest;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DialogClassTest extends AppCompatDialogFragment {
    private DialogListener listener;
    private Button timeBtn;
    private TextView room, timeoff;
    private RadioGroup acGroup;
    private RadioGroup lampGroup;
    private RadioGroup timeGroup;
    private boolean myFlag = false;
    private boolean isMorning = true;
    String acState = "0";
    String lampState = "0";
    String offtime = "OFF";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        //
        Bundle bundle = getArguments();
        final String roomID = bundle.getString("ROOMID","");
        String blockID = bundle.getString("BLOCK", "");
        //
        room = (TextView) view.findViewById(R.id.room);
        acGroup = (RadioGroup) view.findViewById(R.id.acGroup);
        lampGroup = (RadioGroup) view.findViewById(R.id.lampGroup);
        timeGroup = (RadioGroup) view.findViewById(R.id.timeGroup);
        //
        timeoff = (TextView) view.findViewById(R.id.timeOff);
        final Button tiet2 = (Button) view.findViewById(R.id.tiet2);
        final Button tiet3 = (Button) view.findViewById(R.id.tiet3);
        final Button tiet4 = (Button) view.findViewById(R.id.tiet4);
        final Button tiet5 = (Button) view.findViewById(R.id.tiet5);

        tiet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMorning){
                    offtime = "08:40";
                }
                else {
                    offtime = "14:10";
                }
                try {
                    if (compareTime(getcurrentTime(), offtime)) {
                        timeoff.setText("Tắt vào lúc: " + offtime);
                        myFlag = true;
                    }
                    else {
                        timeoff.setText("Thời gian set không phù hợp!");
                        myFlag = false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        tiet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMorning){
                    offtime = "09:40";
                }
                else {
                    offtime = "15:10";
                }
                try {
                    if (compareTime(getcurrentTime(), offtime)) {
                        timeoff.setText("Tắt vào lúc: " + offtime);
                        myFlag = true;
                    }
                    else {
                        timeoff.setText("Thời gian set không phù hợp!");
                        myFlag = false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        tiet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMorning){
                    offtime = "10:40";
                }
                else {
                    offtime = "16:10";
                }
                try {
                    if (compareTime(getcurrentTime(), offtime)) {
                        timeoff.setText("Tắt vào lúc: " + offtime);
                        myFlag = true;
                    }
                    else {
                        timeoff.setText("Thời gian set không phù hợp!");
                        myFlag = false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        tiet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMorning){
                    offtime = "11:30";
                }
                else {
                    offtime = "17:00";
                }
                try {
                    if (compareTime(getcurrentTime(), offtime)) {
                        timeoff.setText("Tắt vào lúc: " + offtime);
                        myFlag = true;
                    }
                    else {
                        timeoff.setText("Thời gian set không phù hợp!");
                        myFlag = false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        acGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.acOn) {
                    acState = "1";
                }
                if(checkedId == R.id.acOFF) {
                    acState = "0";
                }

            }
        });

        lampGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.lampON) {
                    lampState = "1";
                }
                if(checkedId == R.id.lampOFF) {
                    lampState = "0";
                }

            }
        });


        timeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.morning) {
                    isMorning = true;
                    tiet2.setText("Tiết 2");
                    tiet3.setText("Tiết 3");
                    tiet4.setText("Tiết 4");
                    tiet5.setText("Tiết 5");
                }
                if(checkedId == R.id.afternoon) {
                    isMorning = false;
                    tiet2.setText("Tiết 8");
                    tiet3.setText("Tiết 9");
                    tiet4.setText("Tiết 10");
                    tiet5.setText("Tiết 11");
                }
            }
        });

        room.setText("Chọn Thiết bị bạn muốn bật");
        //
        builder.setView(view)
                .setTitle(roomID)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            listener.sendMess(roomID, "LED:" + lampState + " AC:" + acState + " ENDTIME:" + offtime, myFlag);
                    }
                });
        return builder.create();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (DialogListener) context;
        } catch (ClassCastException e) {

        }
    }


    public  interface DialogListener {
        void sendMess(String roomID, String mess, boolean flag);
    }

    public String getcurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    public boolean compareTime(String currenttime, String endtime) throws ParseException {
        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date1 = sdf.parse(currenttime);
            Date date2 = sdf.parse(endtime);

            // Outputs -1 as date1 is before date2
            return date1.compareTo(date2) < 0 ? true : false;

    }
}
