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
        return startTime;
    }
    void setEndTime (int index){
        endTime = Calendar.getInstance();
        System.out.println("종료 시각: " + dateFormat.format(endTime.getTime()));
        rentList.get(index).setEndTime(endTime);
    }

    void testEndTime (int index) {
        test = Calendar.getInstance();
        test.set(Calendar.HOUR_OF_DAY,22); // 테스트 할 시각 입력
        test.set(Calendar.MINUTE,54); // 테스트 할 분 입력
        System.out.println("종료시각: " + dateFormat.format(test.getTime()));
        rentList.get(index).setEndTime(test);
    }

    int getTime(Calendar startTime, Calendar endTime) {
        diffHour = (int)(endTime.getTimeInMillis() - startTime.getTimeInMillis())/1000/(60*60);
        // 1000분의 1초 > 나누기 1000 > 60분 * 60초로 나눔
        diffMin = (int)((endTime.getTimeInMillis() - startTime.getTimeInMillis())/1000/60 - diffHour*60);
        // 1000분의 1초 > 나누기 1000 > 60초로 나눔 > 시간*60초 빼기
        System.out.println("이용시간: " + diffHour + "시간 " + diffMin + "분");
        if (!(diffMin ==0)) {
            return ++diffHour;
        } else {
            return diffHour;
        }
    }
}