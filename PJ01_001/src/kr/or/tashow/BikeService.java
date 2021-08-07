package kr.or.tashow;

import java.util.ArrayList;
import java.util.Scanner;

public class BikeService {
    static ArrayList<Bike> bikeList = new ArrayList<Bike>();
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

    ArrayList<Bike> addBike() {
        Scanner scan = new Scanner(System.in);
        System.out.println("등록하고자 하는 자전거의 종류를 입력하세요");
        System.out.println("1. 1인용 자전거 | 2. 2인용 자전거");
        int input = Integer.parseInt(scan.nextLine());
        System.out.println("자전거 대수를 입력하세요");
        int amount = Integer.parseInt(scan.nextLine());
        if (input == 1) {
            for (int i = 0 ; i < amount ; i++) {
                this.id = String.format("S-%04d",++countSingle);
                this.bikeType = BikeType.Single;
                this.price = 1000;
                bikeList.add(new Bike(this.id,this.bikeType,this.price));
            }

        } else if (input == 2) {
            for (int i = 0 ; i < amount ; i++) {
                this.id = String.format("T-%04d",++countTwin);
                this.bikeType = BikeType.Twin;
                this.price = 2000;
                bikeList.add(new Bike(this.id,this.bikeType,this.price));
            }

        } else {
            System.out.println("잘못 입력");
        }
        System.out.println(bikeList.toString());
        System.out.println("생성된 1인용 자전거 대수: "+countSingle);
        System.out.println("생성된 2인용 자전거 대수: "+countTwin);
        return bikeList;
    }

    void removeBike() {

    }

}
