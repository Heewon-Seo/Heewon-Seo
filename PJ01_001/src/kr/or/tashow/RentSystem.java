package kr.or.tashow;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class RentSystem {
    Time time;


    public RentSystem() {
        time = new Time();
    }

    int calculateFee(String bikeNum, Calendar startTime, Calendar endTime) {// (시작시각 -종료시각) *  1인용 > 1000원 , 2인용 > 2000원
        int fee = 0;
        if(bikeNum.startsWith("S")) {
            fee = time.getTime(startTime,endTime)*1000;
        }else if(bikeNum.startsWith("T")) {
            fee = time.getTime(startTime,endTime)*2000;
        }else{
            System.out.println("잘못입력");
        }
        return fee;
    }

    void writeUserList() {// Filewrite > UserList
        File userList =new File("/Users/heewonseo/Documents/1stProject/lists/userlist.txt");
        FileOutputStream fos =null;
        BufferedOutputStream bos =null;
        ObjectOutputStream oos =null;

        try{
            fos =new FileOutputStream(userList);
            bos =new BufferedOutputStream(fos);
            oos =new ObjectOutputStream(bos);

            oos.writeObject(User.userList);
            System.out.println("저장되었습니다.");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }finally{
            try{
                oos.close();
                bos.close();
                fos.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void writeBikeList() {
        File file = new File("/Users/heewonseo/Documents/1stProject/lists/bikelist.txt");
        FileOutputStream fos =null;
        BufferedOutputStream bos =null;
        ObjectOutputStream oos =null;

        try{
            fos =new FileOutputStream(file);
            bos =new BufferedOutputStream(fos);
            oos =new ObjectOutputStream(bos);

            oos.writeObject(Bike.bikeList);
            System.out.println("저장되었습니다.");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }finally{
            try{
                oos.close();
                bos.close();
                fos.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readBikeList () {
        File file =new File("/Users/heewonseo/Documents/1stProject/lists/bikelist.txt");
        FileInputStream fis =null;
        BufferedInputStream bis =null;
        ObjectInputStream ois =null;

        try{
            fis =new FileInputStream(file);
            bis =new BufferedInputStream(fis);
            ois =new ObjectInputStream(bis);

            Bike.bikeList= (ArrayList<Bike>) ois.readObject();

// String bikeNum, String bikeType, int fee, int countSingle, int countTwin, boolean rentStatus

            for(Bike bike : Bike.bikeList) {
                System.out.println(bike);
            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }finally{
            try{
                ois.close();
                bis.close();
                fis.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    void writeRentList() {

    }

    void calculateTotalSales() {

    }
}
