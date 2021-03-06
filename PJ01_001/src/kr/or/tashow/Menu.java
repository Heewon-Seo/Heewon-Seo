package kr.or.tashow;

import java.util.Scanner;

public class Menu {
    static String cur_user_id;
    Scanner input;
    User user;
    Admin admin;
    FileIO fileIo;
    BikeService bikeService;
    UserSystem userSystem;
    AdminSystem adminSystem;

    public Menu() {
        cur_user_id = "";
        input = new Scanner(System.in);
        user = new User();
        admin = new Admin();
        fileIo = new FileIO();
        bikeService = new BikeService();
        userSystem = new UserSystem();
        adminSystem = new AdminSystem();
        fileIo.startSystem();
    }

    public void displayDefaultMenu() { // 첫 메뉴 번호입력
        // 회원가입, 사용자 인증, 관리자 인증, 프로그램 종료
        while (true) {
            System.out.println();
            System.out.println("******비트를 타쇼 자전거 대여 시스템******");
            System.out.println("     원하는 메뉴의 번호를 입력하세요");
            System.out.println("        1. 회원 가입");
            System.out.println("        2. 사용자 인증");
            System.out.println("        3. 관리자 인증");
            System.out.println("        4. 프로그램 종료");
            System.out.println("**********************************");
            int menu;
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
                    userSystem.signUp();
                    break;
                case 2:
                    if (!(userSystem.userLogin() == 1)) {
                        displayUserMenu();
                    }
                    break;
                case 3:
                    if ((adminSystem.adminLogin())) {
                        displayAdminMenu();
                    }
                    break;
                case 4:
                    System.out.println("이용해주셔서 감사합니다!");
                    return;
            }

        }
    }

    private void displayAdminMenu() { // 관리자 메뉴
        // 매출관리, 회원목록조회, 자전거관리, 프로그램 종료

        while (true) {
            System.out.println();
            System.out.println("*************비트를 타쇼 관리자 메뉴************");
            System.out.println("        원하는 메뉴의 번호를 입력하세요");
            System.out.println("            1. 매출 조회");
            System.out.println("            2. 회원 목록 조회");
            System.out.println("            3. 자전거 관리");
            System.out.println("            4. 초기 화면");
            System.out.println("******************************************");
            int menu;

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
                    fileIo.readUserList();
                    break;
                case 3:
                    displayBikeMenu();
                    break;
                case 4:
                    return;
            }
        }
    }

    private void displayUserMenu() { // 사용자 메뉴
        // 대여하기 (> 자전거 목록조회), 반납하기 (> 결제 및 반납), 프로그램 종료
        int menu = ViewUtil.displayMenu("자전거 대여 시스템", new String[]{
                "대여하기 (1인용)",
                "대여하기 (2인용)",
                "결제 및 반납하기",
                "이전메뉴"
        });

        switch (menu) {
            case 1:
                bikeService.rentalBike("S");
                break;
            case 2:
                bikeService.rentalBike("T");
                break;
            case 3:
                bikeService.returnBike();
                break;
            case 4:
                return;
        }
    }

    private void displayBikeMenu() { // 자전거 관리 메뉴 번호입력

        while (true) {
            System.out.println();
            System.out.println("***********비트를 타쇼 자전거 관리 메뉴**********");
            System.out.println("        원하는 메뉴의 번호를 입력하세요");
            System.out.println("            1. 자전거 등록");
            System.out.println("            2. 자전거 삭제");
            System.out.println("            3. 자전거 목록 조회");
            System.out.println("            4. 자전거 대여 내역 조회");
            System.out.println("            5. 이전 메뉴");
            System.out.println("******************************************");
            int menu;
            do {
                try {
                    menu = Integer.parseInt(input.nextLine());
                    if (menu >= 1 && menu <= 6) {
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
                    break;
                case 2:
                    adminSystem.removeBike();
                    break;
                case 3:
                    fileIo.readBikeList();
                    break;
                case 4:
                    fileIo.readRentList();
                    break;
                case 5:
                    return;
            }
        }
    }
}