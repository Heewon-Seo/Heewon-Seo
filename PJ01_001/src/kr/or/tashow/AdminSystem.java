package kr.or.tashow;

import java.util.ArrayList;
import java.util.Scanner;

import static kr.or.tashow.BikeService.bikeList;

public class AdminSystem {
    Bike bike;
    BikeService bikeService;
    int countSingle;
    int countTwin;

    public AdminSystem() {
        bike = new Bike();
        bikeService = new BikeService();
        this.countSingle = 0;
        this.countTwin = 0;
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
                bikeList.add(new Bike(String.format("S-%04d",++countSingle),BikeType.Single,1000));
            }
        } else if (input == 2) {
            for (int i = 0 ; i < amount ; i++) {
                bikeList.add(new Bike(String.format("T-%04d",++countTwin),BikeType.Twin,2000));
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
