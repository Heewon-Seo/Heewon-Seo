package kr.or.tashow;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSystem {

    static HashMap<String, User> userList = new HashMap<>();
    User user;
    Scanner input;
    IO io;
    String userPhoneNum;
    String userPwd;
    String userName;

    public UserSystem() {
        user = new User();
        input = new Scanner(System.in);
        io = new IO();
        userPhoneNum = "";
        userPwd = "";
        userName = "";
    }

    private Pattern userPhonNumPattern = Pattern.compile("^01(0|1|6|7|8|9)-\\d{3,4}-\\d{4}$");
    private Pattern userNamePattern = Pattern.compile("^[가-힣]*$");// 이름 한글만
    private Pattern userPwdPattern = Pattern.compile("^[a-zA-Z0-9]{6,8}$"); // 비밀번호 형식

    void singUp() {
        while (true) {
            System.out.println("휴대폰 번호(ID) 입력");
            userPhoneNum = input.nextLine().trim();
            Matcher phone = userPhonNumPattern.matcher(userPhoneNum);

            if (!phone.find()) {
                System.out.println("형식오류. 재입력");
                System.out.println("ex) 010-1234-5678");

            } else if (userList.containsKey(userPhoneNum)) {
                System.out.println("이미 등록된 아이디입니다.");

            } else {
                System.out.println("비밀번호를 입력해주세요");
                System.out.println("------------------------");
                System.out.println("비밀번호 형식 : \n1. 최소 영문 하나 이상 포함\n2. 특수 문자(!@#$%^&*?_~),숫자,영문(대소문자)만 가능\n3. 6글자 ~ 8글자");
                System.out.println("------------------------");
                userPwd = input.nextLine();
                Matcher pwd = userPwdPattern.matcher(this.userPwd);

                if (!pwd.find()) {
                    System.out.println("형식 오류. 재입력");
                } else {
                    System.out.println("이름 입력");
                    userName = input.nextLine();

                    Matcher name = userNamePattern.matcher(this.userName);
                    if (!name.find()) {
                        System.out.println("형식 오류. 재입력");
                        System.out.println("ex) 홍길동 ");

                    } else {
                        userList.put(userPhoneNum, new User(userPhoneNum, userName, userPwd));
                        io.writeUserList();
                        showResult();
                        break;
                    }
                }
            }
        }
    }

    void showResult() {   //입력받은 값을 보여주기
        System.out.println("회원가입이 완료되었습니다");
        System.out.println("*****************************************************************");
        System.out.println("\t이름 : " + userName + ", \tID : " + userPhoneNum + ",\t 비밀번호 : " + userPwd);
        System.out.println("*****************************************************************");
        System.out.println();
    }

    String userLogin() {

        while (true) {
            System.out.println("ID를 입력해주세요");
            userPhoneNum = input.nextLine().trim();
            Matcher phone = userPhonNumPattern.matcher(userPhoneNum);
            if (!phone.find()) {
                System.out.println("휴대폰 번호 양식에 맞춰서 다시 입력해주세요");
                System.out.println("ex) 010-1234-5678");
            } else if (!userList.containsKey(userPhoneNum)) {
                System.out.println("ID를 찾을 수 없습니다");
                System.out.println("다시 한 번 정확히 입력해 주세요");
            } else {
                for (int cnt = 3; cnt > 0; cnt--) {
                    System.out.println("비밀번호를 입력해주세요");
                    userPwd = input.nextLine();
                    if (userList.get(userPhoneNum).getUserPwd().equals(userPwd)) {
                        System.out.println();
                        System.out.println("***************");
                        System.out.println(userList.get(userPhoneNum).getUserName() + "님, 환영합니다!");
                        System.out.println("***************");
                        System.out.println();
                        Menu.cur_user_id = userPhoneNum;
                        break;
                    } else {
                        if (cnt > 1) {
                            System.out.println("입력하신 비밀번호가 잘못되었습니다");
                            System.out.print((cnt - 1) + "번의 기회가 남았습니다. 올바른 ");
                        } else {
                            System.out.println("입력하신 비밀번호가 잘못되었습니다");
                            System.out.println("처음으로 돌아갑니다"); // 안돌아감
                        }
                    }
                }
            }
            return userPhoneNum;
        }
    }
}
