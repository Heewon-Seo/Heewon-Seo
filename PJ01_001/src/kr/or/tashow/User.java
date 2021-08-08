package kr.or.tashow;

import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable {
    private Scanner input;

    private String userPhoneNum;
    private String userName;
    private String userPwd;

    private Time time;
    private IO rentSystem;

    public User() {
        input = new Scanner(System.in);
        rentSystem = new IO();
        time = new Time();
    }

    public User(String userPhoneNum, String userName, String userPwd) {
        this.userPhoneNum = userPhoneNum;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public IO getRentSystem() {
        return rentSystem;
    }

    public void setRentSystem(IO rentSystem) {
        this.rentSystem = rentSystem;
    }

    @Override
    public String toString() {
        return "[" +
                "휴대폰번호(ID): '" + userPhoneNum + '\'' +
                "\t 이름: " + userName + '\'' +
                "\t 비밀번호" + userPwd + '\'' +
                "]\n";
    }
}
