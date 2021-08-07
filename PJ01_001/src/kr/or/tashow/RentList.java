package kr.or.tashow;

import java.util.Calendar;
import java.util.HashMap;

public class RentList {
    String id;
    Calendar startTime;
    Calendar endTime;
    Bike bike;
    static HashMap<String,RentList> rentList = new HashMap<>();

    public RentList(String id) {
        this.id = id;
        bike = new Bike();
    }
}
