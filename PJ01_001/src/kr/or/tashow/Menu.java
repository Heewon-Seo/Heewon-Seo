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
        io.startSystem();
    }

    public void displayDefaultMenu() { // 첫 메뉴 번호입력
        // 회원가입, 사용자 인증, 관리자 인증, 프로그램 종료
        while(true) {
            System.out.println("*****비트를 타쇼 자전거 대여 시스템*****");
            System.out.println("원하는 메뉴의 번호를 입력하세요");
            System.out.println("1. 회원가입");
            System.out.println("2. 사용자인증");
            System.out.println("3. 관리자인증");
            System.out.println("4. 프로그램종료");

            int menu = 0;
        do {
            try {
                menu = Integer.parseInt(input.nextLine());
                if (menu >= 1 && menu <= 4) {
                    break;
                } else {
                    throw new Exception("메뉴 번호 오류");
                }

            } catch (Exception e) {
                System.out.println("오류발생: " + e.getMessage());
                System.out.println("1~4 중 다시 입력해주세요");
            }
        } while(true);

            switch (menu) {
                case 1:
                    userSystem.singUp();
                    break;
                case 2:
                    userSystem.userLogin();
                    displayUserMenu();
                    break;
                case 3:
                    adminSystem.adminLogin();
                    displayAdminMenu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("잘못입력");
                    break;
            }

        }
    }

    private void displayAdminMenu() { // 관리자 메뉴
        // 매출관리, 회원목록조회, 자전거관리, 프로그램 종료

        while(true) {
            System.out.println("원하는 메뉴의 번호를 입력하세요");
            System.out.println("1. 매출조회");
            System.out.println("2. 회원목록조회");
            System.out.println("3. 자전거관리");
            System.out.println("4. 초기화면으로");
            int menu = 0;

        do {
            try {
                menu = Integer.parseInt(input.nextLine());
                if (menu >= 1 && menu <= 4) {
                    break;
                } else {
                    throw new Exception("메뉴 번호 오류");
                }
            } catch (Exception e) {
                System.out.println("오류발생: " + e.getMessage());
                System.out.println("1~4 중 다시 입력해주세요");
            }
        } while (true);
                switch (menu) {
                    case 1:
                        bikeService.calculateTotalSales();
                        break;
                    case 2:
                        io.readUserList();
                        break;
                    case 3:
                        displayBikeMenu();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("잘못입력");
                        break;
                }
        }
    }

    private void displayUserMenu() { // 사용자 메뉴
        // 대여하기 (> 자전거 목록조회), 반납하기 (> 결제 및 반납), 프로그램 종료

        while(true) {

            System.out.println("원하는 메뉴의 번호를 입력하세요");
            System.out.println("1. 대여하기 (1인용)");
            System.out.println("2. 대여하기 (2인용)");
            System.out.println("3. 결제 및 반납하기");
            System.out.println("4. 이전메뉴");
            int menu = 0;
            do {
                try {
                    menu = Integer.parseInt(input.nextLine());
                    if (menu >= 1 && menu <= 4) {
                        break;
                    } else {
                        throw new Exception("메뉴 번호 오류");
                    }
                } catch (Exception e) {
                    System.out.println("오류발생: " + e.getMessage());
                    System.out.println("1~4 중 다시 입력해주세요");
                }
            } while (true);

                switch (menu) {
                    case 1:
                        bikeService.rentalBike("S");
                        break;
                    case 2:
                        bikeService.rentalBike("T");
                        break;
                    case 3:
                        System.out.println("반납할 자전거의 일련번호를 입력해주세요");
                        String id = input.nextLine();
                        bikeService.returnBike(id);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("잘못입력");
                        break;
                }
            }
    }

    private void displayBikeMenu() { // 자전거 관리 메뉴 번호입력
        System.out.println("원하는 메뉴의 번호를 입력하세요");
        System.out.println("1. 자전거등록");
        System.out.println("2. 자전거삭제");
        System.out.println("3. 자전거목록조회");
        System.out.println("4. 자전거대여내역조회");
        System.out.println("5. 이전메뉴");
        int menu = 0;
        do {
            try {
                menu = Integer.parseInt(input.nextLine());
                if (menu >= 1 && menu <= 5) {
                    break;
                } else {
                    throw new Exception("메뉴 번호 오류");
                }
            } catch (Exception e) {
                System.out.println("오류발생: " + e.getMessage());
                System.out.println("1~5 중 다시 입력해주세요");
            }
        } while (true);

            switch (menu) {
                case 1:
                    adminSystem.addBike();
                    io.writeBikeList();
                    break;
                case 2:
                    adminSystem.removeBike();
                    break;
                case 3:
                    io.readBikeList();
                    break;
                case 4:
                    io.readRentList();
                    break;
                case 5:
                    break; // 이전메뉴 동작 안함
                default:
                    System.out.println("잘못입력");
                    break;
            }
        }

}