package kr.or.tashow;

import javax.xml.stream.events.EndDocument;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RentList implements Serializable {
    private String id;
    private String userPhoneNum;
    private Calendar startTime;
    private Calendar endTime;
    private String startTimeStr;
    private String endTimeStr;
    private DateFormat format;
    private int fee;

    RentList () {
        this("",null);
        this.fee = 0;
        format = new SimpleDateFormat("HH:mm");
        startTimeStr = format.format(startTime);
        endTimeStr = format.format(endTime);
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
        return "====================================" +
                "일련번호: " + id + '\'' +
                "\t 대여자ID: '" + userPhoneNum + '\'' +
                "\n 대여 시작: " + startTimeStr +
                "\n 대여 종료" + endTimeStr +
                "\n 총 요금: " + fee +
                "====================================\n";
    }
}
