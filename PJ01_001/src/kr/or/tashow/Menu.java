package kr.or.tashow;

import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    User user;
    Admin admin;
    IO io;
    BikeService bikeService;
    UserSystem userSystem;
    AdminSystem adminSystem;

    public Menu() {
        user = new User();
        admin = new Admin();
        io = new IO();
        bikeService = new BikeService();
        userSystem = new UserSystem();
        adminSystem = new AdminSystem();
        io.initialize();
    }


    public void displayDefaultMenu() {
        // 회원가입, 사용자 인증, 관리자 인증, 프로그램 종료
        while(true) {
            System.out.println("원하는 메뉴를 입력하세요");
            System.out.println("1. 회원가입");
            System.out.println("2. 사용자인증");
            System.out.println("3. 관리자인증");
            System.out.println("4. 프로그램종료");
            int menu = Integer.parseInt(input.nextLine());

            if(menu >= 1 && menu <= 4) {
                switch (menu) {
                    case 1:
                        userSystem.singUp();
                        break;
                    case 2:
                        userSystem.userLogin();
                        displayUserMenu();
                        break;
                    case 3: displayAdminMenu(); break;
                    case 4: return;
                }
            } else {
                System.out.println("잘못입력");
                return;
            }
        }
    }

    void displayAdminMenu() {
        // 매출관리, 회원목록조회, 자전거관리, 프로그램 종료
        while(true) {
            System.out.println("원하는 메뉴를 입력하세요");
            System.out.println("1. 매출조회");
            System.out.println("2. 회원목록조회");
            System.out.println("3. 자전거관리");
            System.out.println("4. 이전메뉴");

            int menu = Integer.parseInt(input.nextLine());
            if(menu >= 1 && menu <= 4) {
                switch (menu) {
                    case 1: bikeService.calculateTotalSales(); break;
                    case 2: io.readUserList(); break;
                    case 3: displayBikeMenu(); break;
                    case 4: return;
                }
            } else {
                System.out.println("잘못입력");
                return;
            }
        }
    }

    void displayUserMenu() {
        // 대여하기 (> 자전거 목록조회), 반납하기 (> 결제 및 반납), 프로그램 종료

        while(true) {

            System.out.println("1. 대여하기 (1인용)");
            System.out.println("2. 대여하기 (2인용)");
            System.out.println("3. 결제 및 반납하기");
            System.out.println("4. 이전메뉴");

            int menu = Integer.parseInt(input.nextLine());
            if(menu >= 1 && menu <= 4) {
                switch (menu) {
                    case 1: bikeService.rentalBike("S"); break;
                    case 2: bikeService.rentalBike("T"); break;
                    case 3:
                        System.out.println("반납할 자전거의 일련번호를 입력해주세요");
                        String id = input.nextLine();
                        bikeService.returnBike(id);
                        break;
                    case 4: return;
                }
            } else {
                System.out.println("잘못입력");
                return;
            }
        }
    }

    void displayBikeMenu() {

        while(true) {

            System.out.println("1. 자전거등록");
            System.out.println("2. 자전거삭제");
            System.out.println("3. 자전거목록조회");
            System.out.println("4. 자전거대여내역조회");
            System.out.println("5. 이전메뉴");

            int menu = Integer.parseInt(input.nextLine());
            if(menu >= 1 && menu <= 4) {
                switch (menu) {
                    case 1:
                        adminSystem.addBike();
                        io.writeBikeList();
                        break;
                    case 2: adminSystem.removeBike(); break;
                    case 3: io.readBikeList(); break;
                    case 4: io.readRentList(); break;
                    case 5: return;
                }
            } else {
                System.out.println("잘못입력");
                return;
            }
        }
    }
}