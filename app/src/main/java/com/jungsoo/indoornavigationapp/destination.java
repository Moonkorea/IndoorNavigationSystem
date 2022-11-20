package com.jungsoo.indoornavigationapp;

public class destination {

    private String roomNum;
    private String roomName;

    public destination(String roomNum, String roomName) {
        this.roomNum = roomNum;
        this.roomName = roomName;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

}
