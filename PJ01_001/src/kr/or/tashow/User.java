package kr.or.tashow;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    String userPhoneNum;
    String userName;
    String userPwd;
    HashMap<String,User> userList = new HashMap<String,User>();
    Scanner input;
    Pattern pt = Pattern.compile("^01(0|1|6|7|8|9)-\\d{3,4}-\\d{4}$");

    public User(String userPhoneNum, String userName, String userPwd, HashMap<String, User> userList, Scanner input) {
        this.userPhoneNum = userPhoneNum;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userList = userList;
        input = new Scanner(System.in);
    }

    public HashMap insertUserInfo(User user) {
        System.out.println("휴대폰 번호 입력");
        user.userPhoneNum = input.nextLine();
        System.out.println("이름 입력");
        user.userName = input.nextLine();
        System.out.println("비밀번호 입력");
        user.userPwd = input.nextLine();
        user.userList.put(user.userPhoneNum, user);
        System.out.println(user.toString());
        System.out.println(user.userList.toString());
        return user.userList;
    }

    void showResult () {   //입력받은 값을 보여주기
        System.out.println("회원가입이 완료되었습니다");
        System.out.println("*****************************************************************");
        System.out.println("\t이름 : " + this.userName + ", \tID : " + this.userPhoneNum + ",\t 비밀번호 : " + this.userPwd);
        System.out.println("*****************************************************************");
        System.out.println();
    }

    public void userLogin () {

        while (true) {
            System.out.println("ID를 입력해주세요");
            this.userPhoneNum = input.nextLine().trim().toLowerCase();

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
                if (userList.get(this.userPhoneNum).equals(this.userPwd)) {
                    System.out.println("인증 완료되었습니다");
                    break;
                } else {
                    System.out.println("비밀번호 오류입니다. 재입력");
                }
            }
        }
    }
}
