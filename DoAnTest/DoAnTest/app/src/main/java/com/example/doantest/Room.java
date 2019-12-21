package com.example.doantest;

public class Room {
    private String roomId;
    private String lampStatus;
    private String acStatus;
    private String fanStatus;
    private String endTime;

    public Room() {
    }

    public Room(String roomId, String lampStatus, String acStatus, String fanStatus, String endTime) {
        this.roomId = roomId;
        this.lampStatus = lampStatus;
        this.acStatus = acStatus;
        this.fanStatus = fanStatus;
        this.endTime = endTime;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getLampStatus() {
        return lampStatus;
    }

    public void setLampStatus(String lampStatus) {
        this.lampStatus = lampStatus;
    }

    public String getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(String acStatus) {
        this.acStatus = acStatus;
    }

    public String getFanStatus() {
        return fanStatus;
    }

    public void setFanStatus(String fanStatus) {
        this.fanStatus = fanStatus;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
