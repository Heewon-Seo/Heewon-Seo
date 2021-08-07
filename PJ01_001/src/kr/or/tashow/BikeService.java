package kr.or.tashow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class BikeService {
    static ArrayList<Bike> bikeList = new ArrayList<Bike>();
    //휴대폰번호(key), 대여시작시각, 대여종료시각, 결제요금
    static ArrayList<RentList> rentList = new ArrayList<RentList>();
    Time time;
    IO io;

    void rentalBike(BikeType type) { // 대여
        for (int i = 0 ; i < bikeList.size() ; i++) {
            if (bikeList.get(i).getType().equals(type) && bikeList.get(i).getRentalStatus().equals(RentalStatus.AVAILABLE)) {
                bikeList.get(i).setRentalStatus(RentalStatus.UNAVAILABLE);
                System.out.println("대여된 자전거: " + bikeList.get(i).getId() + bikeList.get(i).getRentalStatus());
                time.inputStartTime(userPhoneNum, i);
                io.writeBikeList();
                io.writeRentList();
                break;
            }
        }
    }

    Bike returnBike(String id) { // 반납

        for(Bike bike: bikeList) { // bikeList
            io.loadRentList();
            if (bike.getId().equals(id)) { // 리스트에 아이디값과 인자로 받은 아이디값과 같으면
                bike.setRentalStatus(RentalStatus.AVAILABLE); // 대여가능으로 바꿔주고
                time.inputEndTime(userPhoneNum);
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
