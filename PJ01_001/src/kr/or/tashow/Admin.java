package kr.or.tashow;

import java.util.ArrayList;
import java.util.Scanner;
import static kr.or.tashow.Bike.bikeList;

public class Admin {
    String id;
    BikeType bikeType;
    int price;
    int countSingle;
    int countTwin;


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
    }
