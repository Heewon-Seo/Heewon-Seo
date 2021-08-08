package kr.or.tashow;

import java.util.HashMap;
import java.util.Scanner;

import static kr.or.tashow.BikeService.bikeList;

public class AdminSystem {
    Bike bike;
    String id;
    String pw;
    BikeService bikeService;
    IO io;
    HashMap<String, String> adminMap = new HashMap<>();

    public AdminSystem() {
        bike = new Bike();
        bikeService = new BikeService();
        io = new IO();
        this.id = "";
        this.pw = "";
        adminMap.put("admin", "admin!");
    }

    HashMap<String, Bike> addBike() { // try, catch 해야됨
        Scanner scan = new Scanner(System.in);
        System.out.println("등록하고자 하는 자전거의 종류를 입력하세요");
        System.out.println("1. 1인용 자전거 | 2. 2인용 자전거");
        int input = Integer.parseInt(scan.nextLine());
        if (!(input == 1 || input == 2)) {
            System.out.println("1인용 혹은 2인용만 등록 가능합니다");
            System.out.println("1인용은 1번, 2인용은 2번을 입력해 주세요");
        } else {
            System.out.println("등록할 자전거 대수를 입력하세요");
            int amount = Integer.parseInt(scan.nextLine());
            String bikeId = "";
            if (input == 1) {
                for (int i = 0; i < amount; i++) {
                    bikeId = String.format("S-%04d", bikeList.size());
                    bikeList.put(bikeId, new Bike(BikeType.Single, 1000));
                    io.writeBikeList();
                    System.out.println("[" + bikeId + "가 등록되었습니다]");
                }
            } else if (input == 2) {
                for (int i = 0; i < amount; i++) {
                    bikeId = String.format("T-%04d", bikeList.size());
                    bikeList.put(bikeId, new Bike(BikeType.Twin, 2000));
                    io.writeBikeList();
                    System.out.println("[" + bikeId + "가 등록되었습니다]");
                }
            } else {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
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
