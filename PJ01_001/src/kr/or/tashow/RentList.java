package kr.or.tashow;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class RentList implements Serializable {
    String id;
    Calendar startTime;
    Calendar endTime;
    Bike bike;
    //휴대폰번호(key), 대여시작시각, 대여종료시각, 결제요금
    static HashMap<String,RentList> rentList = new HashMap<>();

    public RentList(String id) {
        this.id = id;
        bike = new Bike();
    }

    @Override
    public String toString() {
        return "RentList{" +
                ", id='" + id + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}' + "\n";
    }
}
