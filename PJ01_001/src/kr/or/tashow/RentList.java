package kr.or.tashow;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class RentList implements Serializable {
    String id;
    String userPhoneNum;
    Calendar startTime;
    Calendar endTime;

    public RentList(String id, String userPhoneNum) {
        this.id = id;
        this.userPhoneNum = userPhoneNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
}
