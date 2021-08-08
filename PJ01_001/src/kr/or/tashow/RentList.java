package kr.or.tashow;

import java.io.Serializable;
import java.util.Calendar;

public class RentList implements Serializable {
    private String id;
    private String userPhoneNum;
    private Calendar startTime;
    private Calendar endTime;
    private int fee;

    RentList () {
        this("",null);
        this.fee = 0;
    }

    RentList(String id, Calendar startTime) {
        this.id = id;
        this.startTime = startTime;
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

    public int getFee() { return fee; }

    public void setFee(int fee) { this.fee = fee; }

    @Override
    public String toString() {
        return "RentList{" +
                "id='" + id + '\'' +
                ", userPhoneNum='" + userPhoneNum + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", fee=" + fee +
                '}';
    }
}
