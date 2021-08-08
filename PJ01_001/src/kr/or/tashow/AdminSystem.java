package kr.or.tashow;

import java.util.HashMap;
import java.util.Scanner;

import static kr.or.tashow.BikeService.bikeList;

public class AdminSystem {
    Bike bike;
    String id;
    String pw;
    BikeService bikeService;
    int countSingle;
    int countTwin;
    HashMap<String, String> adminMap = new HashMap<>();

    public AdminSystem() {
        bike = new Bike();
        bikeService = new BikeService();
        this.countSingle = 0;
        this.countTwin = 0;
        this.id = "";
        this.pw = "";
        adminMap.put("admin", "admin!");
    }

    HashMap<String, Bike> addBike() {
        Scanner scan = new Scanner(System.in);
        System.out.println("등록하고자 하는 자전거의 종류를 입력하세요");
        System.out.println("1. 1인용 자전거 | 2. 2인용 자전거");
        int input = Integer.parseInt(scan.nextLine());
        if (!(input == 1 || input == 2)) {
            System.out.println("다시 입력해주세요.");
        } else {
            System.out.println("자전거 대수를 입력하세요");
            int amount = Integer.parseInt(scan.nextLine());
            String bikeId = "";
            if (input == 1) {
                for (int i = 0; i < amount; i++) {
                    bikeId = String.format("S-%04d", ++countSingle);
                    bikeList.put(bikeId, new Bike(BikeType.Single, 1000));
                }
            } else if (input == 2) {
                for (int i = 0; i < amount; i++) {
                    bikeId = String.format("T-%04d", ++countTwin);
                    bikeList.put(bikeId, new Bike(BikeType.Twin, 2000));
                }
            } else {
                System.out.println("잘못 입력");
            }
        }
        System.out.println(bikeList.toString());
        System.out.println("생성된 1인용 자전거 대수: " + countSingle);
        System.out.println("생성된 2인용 자전거 대수: " + countTwin);
        return bikeList;
    }

    void removeBike() {


    }

    void adminLogin() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("관리자 ID를 입력해주세요");
            String id = sc.nextLine().trim().toLowerCase();

            System.out.println("관리자 비밀번호를 입력해주세요");
            String pwd = sc.nextLine().trim();

            if (!adminMap.containsKey(id)) {
                System.out.println("id가 맞지 않습니다. 재입력 해주세요.");
                continue;
            } else {
                if (adminMap.get(id).equals(pwd)) {
                    System.out.println("관리자 인증이 완료되었습니다.");
                    break;
                } else {
                    System.out.println("비밀번호가 맞지 않습니다. 재입력 해주세요");
                    continue;
                }
            }
        }
    }
}
