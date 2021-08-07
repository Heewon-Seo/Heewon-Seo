package kr.or.tashow;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class RentList implements Serializable {
    String id;
    Calendar startTime;
    Calendar endTime;
    Bike bike;


    public RentList(String id) {
        this.id = id;
        bike = new Bike();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public static ArrayList<RentList> getRentList() {
        return rentList;
    }

    public static void setRentList(ArrayList<RentList> rentList) {
        RentList.rentList = rentList;
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
