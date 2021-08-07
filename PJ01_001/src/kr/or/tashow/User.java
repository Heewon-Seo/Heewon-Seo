package kr.or.tashow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static kr.or.tashow.Bike.bikeList;

public class User implements Serializable {
    String userPhoneNum;
    String userName;
    String userPwd;
    static HashMap<String,User> userList = new HashMap<String,User>();
    Scanner input;
    Pattern pt = Pattern.compile("^01(0|1|6|7|8|9)-\\d{3,4}-\\d{4}$");

    User() {
        input = new Scanner(System.in);

    }

    public User(String userPhoneNum, String userName, String userPwd) {
        this.userPhoneNum = userPhoneNum;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public HashMap singUp() {
        System.out.println("휴대폰 번호(ID) 입력");
        this.userPhoneNum = input.nextLine().trim();

        Matcher ch = pt.matcher(this.userPhoneNum);

        if (ch.find() == false) {
            System.out.println("형식오류. 재입력");
            System.out.println("ex) 010-1234-5678 ");

        } else {
            System.out.println("비밀번호를 입력해주세요");
            this.userPwd = input.nextLine();

            System.out.println("이름 입력");
            this.userName = input.nextLine();

            this.userList.put(this.userPhoneNum, new User(this.userPhoneNum, this.userName, this.userPwd));
        }
        System.out.println(userList.toString());
        showResult();
        return userList;
    }

    void showResult () {   //입력받은 값을 보여주기
        System.out.println("회원가입이 완료되었습니다");
        System.out.println("*****************************************************************");
        System.out.println("\t이름 : " + this.userName + ", \tID : " + this.userPhoneNum + ",\t 비밀번호 : " + this.userPwd);
        System.out.println("*****************************************************************");
        System.out.println();
    }

    public String userLogin () {

        while (true) {
            System.out.println("ID를 입력해주세요");
            this.userPhoneNum = input.nextLine().trim();

            Matcher ch = pt.matcher(this.userPhoneNum);
            if (ch.find() == false) {
                System.out.println("형식오류. 재입력");
                System.out.println("ex) 010-1234-5678 ");

            } else {
                System.out.println("비밀번호를 입력해주세요");
                this.userPwd = input.nextLine();

            }if (!userList.containsKey(this.userPhoneNum)) {
                System.out.println("ID가 맞지 않습니다.재입력");
            } else {
                if (userList.get(this.userPhoneNum).userPwd.equals(this.userPwd)) {
                    System.out.println("인증 완료되었습니다");
                    break;
                } else {
                    System.out.println("비밀번호 오류입니다. 재입력");
                }
            }
        }
        return userPhoneNum;
    }

    public Bike rentalBike(BikeType type) { // 대여
        for(Bike bike: bikeList) { // bikeList
            if (bike.getType() == type && bike.getRentalStatus() == RentalStatus.AVAILABLE) {
                bike.setRentalStatus(RentalStatus.UNAVAILABLE);
                return bike;
            }
            System.out.println(bike);
        }
        return null;
    }

    public Bike returnBike(String id) { // 반납

        for(Bike bike: bikeList) { // bikeList
            if (bike.getId().equals(id)) { // 리스트에 아이디값과 인자로 받은 아이디값과 같으면
                bike.setRentalStatus(RentalStatus.AVAILABLE); // 대여가능으로 바꿔주고
                return bike; // 바이크에 리턴해준다.
            }
        }
        return null;

    }

    @Override
    public String toString() {
        return "User{" +
                "userPhoneNum='" + userPhoneNum + '\'' +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
