package com.example.doantest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class RoomStatusActitivty extends AppCompatActivity {

    private RecyclerView roomlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_status_actitivty);
        roomlist = (RecyclerView)findViewById(R.id.roomlist);
        new FirebaseDataHelper().readBooks(new FirebaseDataHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Room> rooms, List<String> keys) {
                new RoomRecycler_Config().setConfig(roomlist, RoomStatusActitivty.this, rooms, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
