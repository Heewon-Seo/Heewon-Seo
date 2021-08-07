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

    public Calendar inputStartTime (){
        startTime = Calendar.getInstance();
        System.out.println(dateFormat.format(startTime.getTime()));
        return startTime;
    }
    public Calendar inputEndTime (){
        endTime = Calendar.getInstance();
        System.out.println(dateFormat.format(endTime.getTime()));
        return endTime;
    }

    public void inputStartTime(String userPhoneNum, int index) {
        String id = Bike.bikeList.get(index).getId();
        RentList.rentList.put(userPhoneNum,new RentList(id));
        RentList.rentList.get(userPhoneNum).startTime = inputStartTime();
    }
    public void inputEndTime(String userPhoneNum, int index) {
        String id = Bike.bikeList.get(index).getId();
        RentList.rentList.put(userPhoneNum,new RentList(id));
        RentList.rentList.get(userPhoneNum).endTime = inputEndTime();
    }

    public int getTime(Calendar startTime, Calendar endTime) {
        diffHour = endTime.get(Calendar.HOUR_OF_DAY) - startTime.get(Calendar.HOUR_OF_DAY);
        diffMinute = endTime.get(Calendar.MINUTE) - startTime.get(Calendar.MINUTE);
        if (diffMinute > 0) {
            return diffHour++;
        } else {
            return diffHour;
        }
    }
}