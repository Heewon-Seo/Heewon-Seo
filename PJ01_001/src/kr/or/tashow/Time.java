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
    int diffMin;

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
        diffHour = (int)(endTime.getTimeInMillis() - startTime.getTimeInMillis())/1000/(60*60);
        diffMin = (int)((endTime.getTimeInMillis() - startTime.getTimeInMillis())/1000/60 - diffHour*60);
        System.out.println("이용시간: " + diffHour + "시간 " + diffMin + "분");
        if (!(diffMin ==0)) {
            return ++diffHour;
        } else {
            return diffHour;
        }
    }
}