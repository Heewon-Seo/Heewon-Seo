package kr.or.tashow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class BikeService implements Serializable {
    static HashMap<String,Bike> bikeList = new HashMap<>();
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
        if (bikeList.isEmpty()) {
            System.out.println("대여 가능한 자전거가 없습니다");
        } else {
            for (String key : bikeList.keySet()) {
                if (key.startsWith(type) && bikeList.get(key).getRentalStatus().equals(RentalStatus.AVAILABLE)) {
                    Bike bike = bikeList.get(key);
                    bike.setRentalStatus(RentalStatus.UNAVAILABLE);
                    System.out.println("대여된 자전거: " + key + bike.getRentalStatus());
                    rentList.add(new RentList(key,time.setStartTime()));
                    io.writeBikeList();
                    io.writeRentList();
                    break;
                }
            }
        }
    }

    void returnBike(String id) { // 반납
        for(String key : bikeList.keySet()) { // bikeList
            io.loadRentList();
            if (bikeList.containsKey(id) && bikeList.get(id).getRentalStatus().equals(RentalStatus.UNAVAILABLE)) { // 리스트에 아이디값과 인자로 받은 아이디값과 같으면
                bikeList.get(id).setRentalStatus(RentalStatus.AVAILABLE);
                for (int i = 0 ; i < rentList.size() ; i++) {
                    if (rentList.get(i).getId().contains(id)) {
                        time.inputEndTime(i);
                    }
                }
                io.writeBikeList();
                io.writeRentList();
                break;
            } else {
                System.out.println("일련번호가 일치하지 않습니다.");
            }
        }
    }

    void payFee() {
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
