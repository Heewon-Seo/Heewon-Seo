package kr.or.tashow;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static kr.or.tashow.BikeService.rentList;

public class Time {
    Calendar startTime;
    Calendar endTime;
    Calendar test;
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

    void inputEndTime(int index) {
        rentList.get(index).setEndTime(setEndTime());
    }

    void testEndTime (int index) {
        test = Calendar.getInstance();
        test.set(Calendar.HOUR_OF_DAY,22);
        test.set(Calendar.MINUTE,54);
        rentList.get(index).setEndTime(test);
    }

    int getTime(Calendar startTime, Calendar endTime) {
        diffHour = endTime.get(Calendar.HOUR_OF_DAY) - startTime.get(Calendar.HOUR_OF_DAY);
        diffMinute = endTime.get(Calendar.MINUTE) - startTime.get(Calendar.MINUTE);
        System.out.println("이용시간: " + diffHour + ":" + diffMinute);
        if (!(diffMinute ==0)) {
            return ++diffHour;
        } else {
            return diffHour;
        }
    }

}