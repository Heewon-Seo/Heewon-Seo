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
            String bikeId;
            if (input == 1) {
                for (int i = 0; i < amount; i++) {
                    bikeId = String.format("S-%04d", bikeList.size());
                    bikeList.put(bikeId, new Bike(BikeType.Single, 1000));
                    io.writeBikeList();
                    System.out.println("[" + bikeId + "가 등록되었습니다]");
                }
            } else {
                for (int i = 0; i < amount; i++) {
                    bikeId = String.format("T-%04d", bikeList.size());
                    bikeList.put(bikeId, new Bike(BikeType.Twin, 2000));
                    io.writeBikeList();
                    System.out.println("[" + bikeId + "가 등록되었습니다]");
                }
            }
        }
        return bikeList;
    }

    void removeBike() {
        io.loadRentList();
        Scanner scan = new Scanner(System.in);
        System.out.println("원하는 삭제 방법을 선택해 주세요");
        System.out.println("1. 특정 자전거 삭제 | 2. 일괄 삭제");
        int menu = Integer.parseInt(scan.nextLine());
        if (!(menu == 1 || menu == 2)) {
            System.out.println("잘못 입력");
        } else if (bikeList.isEmpty()) {
            System.out.println("삭제할 자전거가 없습니다.");
        } else if (menu == 2) {
            bikeList.clear();
            System.out.println("리스트가 초기화 되었습니다");
            io.writeBikeList();
        } else {
            System.out.println("삭제 하고자 하는 자전거의 일련번호를 입력해 주세요");
            String id = scan.nextLine();
            if (!bikeList.containsKey(id)) {
                System.out.println("일치하는 자전거가 없습니다");
            } else if (bikeList.get(id).getRentalStatus().equals(RentalStatus.UNAVAILABLE)) {
                System.out.println("해당 자전거는 대여중 입니다");
            } else {
                bikeList.remove(id);
                System.out.println(id + " 자전거가 삭제되었습니다");
                io.writeBikeList();
            }
        }
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
            } else {
                if (adminMap.get(id).equals(pwd)) {
                    System.out.println("관리자 인증이 완료되었습니다.");
                    break;
                } else {
                    System.out.println("비밀번호가 맞지 않습니다. 재입력 해주세요");
                }
            }
        }
    }
}
