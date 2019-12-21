package com.example.doantest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomRecycler_Config {
    private Context mContext;
    private RoomAdapter mRoomAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Room> rooms, List<String> keys) {
        mContext = context;
        mRoomAdapter = new RoomAdapter(rooms, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRoomAdapter);
    }

    class RoomItemView extends  RecyclerView.ViewHolder {
        private TextView mRoom;
        private TextView mAcStatus;
        private TextView mLampStatus;
        private TextView mFanStatus;
        private TextView mEndTime;

        private String key;

        public RoomItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext)
                    .inflate(R.layout.rooms_recycles, parent, false));

            mRoom = (TextView) itemView.findViewById(R.id.room_textview);
            mAcStatus = (TextView) itemView.findViewById(R.id.acStatus_textview);
            mLampStatus = (TextView) itemView.findViewById(R.id.lampStatus_textview);
            mFanStatus = (TextView) itemView.findViewById(R.id.fanStatus_textview);
            mEndTime = (TextView) itemView.findViewById(R.id.endtime_textview);
        }

        public void bind(Room room, String key) {
            mRoom.setText(room.getRoomId());
            mAcStatus.setText(room.getAcStatus());
            mLampStatus.setText(room.getLampStatus());
            mFanStatus.setText(room.getFanStatus());
            mEndTime.setText(room.getEndTime());
            this.key = key;
        }
    }

    class RoomAdapter extends RecyclerView.Adapter<RoomItemView> {
        private List<Room> mRoomList;
        private List<String> mKeys;

        public RoomAdapter(List<Room> mRoomList, List<String> mKeys) {
            this.mRoomList = mRoomList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RoomItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RoomItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RoomItemView holder, int position) {
            holder.bind(mRoomList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mRoomList.size();
        }
    }
}
