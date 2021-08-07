package kr.or.tashow;

import java.io.*;
import java.util.Calendar;
import java.util.HashMap;

public class RentList implements Serializable {
    String userPhoneNum;
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

    void writeRentList(User user) { // 선택하고 나면 시간 입력됨
        File file = new File("rentlist.txt");
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(rentList);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
