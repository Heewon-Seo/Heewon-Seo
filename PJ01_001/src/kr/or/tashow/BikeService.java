package kr.or.tashow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class BikeService {
    static HashMap<String,Bike> bikeList = new HashMap();
    //휴대폰번호, id, 대여시작시각, 대여종료시각, 결제요금
    static ArrayList<RentList> rentList = new ArrayList<>();
    Time time;
    IO io;
    User user;
    int index;

    public BikeService() {
        time = new Time();
        io = new IO();
        user = new User();
        index = 0;
    }

    void rentalBike(String type) { // 대여
        for (String key : bikeList.keySet()) {
            if (key.startsWith(type) && bikeList.get(key).getRentalStatus().equals(RentalStatus.AVAILABLE)) {
                Bike bike = bikeList.get(key);
                bike.setRentalStatus(RentalStatus.UNAVAILABLE);
                System.out.println("대여된 자전거: " + key + bike.getRentalStatus());
                rentList.add(new RentList(key, user.getUserPhoneNum()));
                time.inputStartTime(index++);
                io.writeBikeList();
                io.writeRentList();
                break;
            }
        }
    }

    Bike returnBike(String id) { // 반납

        for(String key : bikeList.keySet()) { // bikeList
            io.loadRentList();
            if (bikeList.containsKey(id)) { // 리스트에 아이디값과 인자로 받은 아이디값과 같으면
                bikeList.get(id).setRentalStatus(RentalStatus.AVAILABLE);
                rentList.get(index)
                io.writeBikeList();
                io.writeRentList();
                return bike; // 바이크에 리턴해준다.
            } else {
                System.out.println("일련번호가 일치하지 않습니다.");
            }
        }
        return null;
    }



    int calculateFee(String bikeNum, Calendar startTime, Calendar endTime) {// (시작시각 -종료시각) *  1인용 > 1000원 , 2인용 > 2000원
        int fee = 0;
        if(bikeNum.startsWith("S")) {
            fee = time.getTime(startTime,endTime)*1000;
        }else if(bikeNum.startsWith("T")) {
            fee = time.getTime(startTime,endTime)*2000;
        }else{
            System.out.println("잘못입력");
        }
        return fee;
    }

    void calculateTotalSales() {

    }



}
