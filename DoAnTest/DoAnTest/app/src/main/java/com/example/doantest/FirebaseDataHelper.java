package com.example.doantest;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDataHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefRooms;
    private List<Room> rooms = new ArrayList<>();

    public  interface  DataStatus{
        void DataIsLoaded(List<Room> rooms, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDataHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRefRooms = mDatabase.getReference("rooms");
    }

    public  void readBooks(final DataStatus dataStatus) {
        mRefRooms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rooms.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Room room = keyNode.getValue(Room.class);
                    rooms.add(room);
                }
                dataStatus.DataIsLoaded(rooms, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
