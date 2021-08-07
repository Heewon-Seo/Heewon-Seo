package kr.or.tashow;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
    Calendar startTime;
    Calendar endTime;
    SimpleDateFormat dateFormat;
    int diffHour;
    int diffMinute;

    public Time() {
        dateFormat = new SimpleDateFormat("HH:mm");
    }

    Calendar setStartTime (){
        startTime = Calendar.getInstance();
        System.out.println(dateFormat.format(startTime.getTime()));
        return startTime;
    }
    Calendar setEndTime (){
        endTime = Calendar.getInstance();
        System.out.println(dateFormat.format(endTime.getTime()));
        return endTime;
    }

    void inputStartTime(String userPhoneNum, int index) {
        RentList.rentList.get(index).getStartTime();
    }
    void inputEndTime(String userPhoneNum) {
        // RentList.rentList.get(userPhoneNum).endTime = setEndTime();
    }

    int getTime(Calendar startTime, Calendar endTime) {
        diffHour = endTime.get(Calendar.HOUR_OF_DAY) - startTime.get(Calendar.HOUR_OF_DAY);
        diffMinute = endTime.get(Calendar.MINUTE) - startTime.get(Calendar.MINUTE);
        if (diffMinute > 0) {
            return diffHour++;
        } else {
            return diffHour;
        }
    }

}