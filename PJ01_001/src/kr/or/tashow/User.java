package kr.or.tashow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User implements Serializable {
    private Scanner input;

    private String userPhoneNum;
    private String userName;
    private String userPwd;

    private Time time;
    private IO rentSystem;

    static HashMap<String,User> userList = new HashMap<String,User>();

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

    public Pattern getUserPhonNumPattenrn() {
        return userPhonNumPattenrn;
    }

    public void setUserPhonNumPattenrn(Pattern userPhonNumPattenrn) {
        this.userPhonNumPattenrn = userPhonNumPattenrn;
    }

    public Pattern getUserNamePattern() {
        return userNamePattern;
    }

    public void setUserNamePattern(Pattern userNamePattern) {
        this.userNamePattern = userNamePattern;
    }

    public Pattern getUserPwdPattenrn() {
        return userPwdPattenrn;
    }

    public void setUserPwdPattenrn(Pattern userPwdPattenrn) {
        this.userPwdPattenrn = userPwdPattenrn;
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
        return "User{" +
                "userPhoneNum='" + userPhoneNum + '\'' +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
