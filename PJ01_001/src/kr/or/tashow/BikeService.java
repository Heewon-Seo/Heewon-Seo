package kr.or.tashow;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

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
                    System.out.println("====================================================");
                    System.out.println("            고객님의 자전거 번호: " + key);
                    System.out.println("반납 시에 자전거 번호를 입력하셔야 하니, 잘 기억해 주세요!");
                    System.out.println("====================================================");
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
                Bike bike = bikeList.get(id);
                for (int i = 0 ; i < rentList.size() ; i++) {
                    if (rentList.get(i).getId().contains(id)) {
                        // time.inputEndTime(i); > 진짜 시간
                        time.testEndTime(i); // > 테스트용 시간
                        int fee = calculateFee(id,rentList.get(i).getStartTime(),rentList.get(i).getEndTime()); // 시간입력
                        payFee(i,fee,id); // 계산
                        io.writeBikeList();
                        io.writeRentList();
                        break;
                    }
                }
                break;
            } else {
                System.out.println("일련번호가 일치하지 않습니다.");
                break;
            }
        }
    }

    void payFee(int index, int fee, String id) {
        System.out.println("결제요금: " + fee + "원");
        System.out.println("[요금안내] 1시간 당 1인용 : 1000원, 2인용 : 2000원");
        System.out.println("* 요금은 시간 단위로 계산되어 1분 초과 시부터 올림 적용됩니다");
        System.out.println("결제하시겠습니까?");
        System.out.println("1. 예 | 2. 아니오 (취소)");
        Scanner scan = new Scanner(System.in);
        int input = Integer.parseInt(scan.nextLine());
        if (input == 1) {
            rentList.get(index).setFee(fee);
            bikeList.get(id).setRentalStatus(RentalStatus.AVAILABLE); // 반납처리

            io.writeRentList();
            System.out.println("결제가 완료되었습니다.");
            System.out.println("이용해 주셔서 감사합니다.");
        } else if (input == 2) {
            rentList.get(index).setFee(0);
            rentList.get(index).setEndTime(null);
            io.writeRentList();
            System.out.println("결제가 취소되었습니다");
            System.out.println("이전화면으로 돌아갑니다");
        }
    }



    int calculateFee(String bikeNum, Calendar startTime, Calendar endTime) {// (시작시각 -종료시각) *  1인용 > 1000원 , 2인용 > 2000원
        int fee = 0;
        if(bikeNum.startsWith("S")) {
            fee = time.getTime(startTime,endTime)*1000;
        }else if(bikeNum.startsWith("T")) {
            fee = time.getTime(startTime,endTime)*2000;
        }
        return fee;
    }

    void calculateTotalSales() {
        DecimalFormat df = new DecimalFormat("#,###");
        io.loadRentList();
        int fee = 0;
        int totalSales = 0;
        for(int i = 0 ; i < rentList.size() ; i++) {
            fee = rentList.get(i).getFee();
            totalSales += fee;
        }
        System.out.println("=========== 현재 기준 총 매출액 ============");
        System.out.println(df.format(totalSales) + "원");
    }
}
