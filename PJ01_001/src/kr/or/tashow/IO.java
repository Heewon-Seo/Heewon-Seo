package kr.or.tashow;

import java.io.*;
import java.util.*;

public class IO {
    Time time;
    String fileRoot;


    public IO() {
        time = new Time();
        fileRoot = "/Users/heewonseo/Documents/1stProject/lists/";
    }

    void initialize() {
        File file = new File(fileRoot+"userlist.txt");
        File file2 = new File(fileRoot+"rentlist.txt");
        File file3 = new File(fileRoot+"bikelist.txt");

        if(!file.exists()) {
            writeUserList();
        } else if (!file2.exists()) {
            writeRentList();
        } else if (!file3.exists()) {
            writeBikeList();
        }

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;

        try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            User.userList = (HashMap<String, User>) ois.readObject();

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

        try{
            fis = new FileInputStream(file2);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            RentList.rentList = (ArrayList<RentList>) ois.readObject();

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

        try{
            fis = new FileInputStream(file3);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            Bike.bikeList = (ArrayList<Bike>) ois.readObject();

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
        File userList = new File(fileRoot+"userlist.txt");
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;

        try{
            fos = new FileOutputStream(userList);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);

            oos.writeObject(User.userList);
            System.out.println("회원 목록이 저장되었습니다.");
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

    void readUserList() {
        File file =new File(fileRoot+"userlist.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;

        try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            User.userList = (HashMap<String, User>) ois.readObject();

            for (Map.Entry<String,User> entrySet : User.userList.entrySet()) {
                System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
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

    void writeBikeList() {
        File file = new File(fileRoot+"bikelist.txt");
        FileOutputStream fos =null;
        BufferedOutputStream bos =null;
        ObjectOutputStream oos =null;

        try{
            fos =new FileOutputStream(file);
            bos =new BufferedOutputStream(fos);
            oos =new ObjectOutputStream(bos);

            oos.writeObject(Bike.bikeList);
            System.out.println("자전거 목록이 저장되었습니다.");
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

    void readBikeList () {
        File file =new File(fileRoot+"bikelist.txt");
        FileInputStream fis =null;
        BufferedInputStream bis =null;
        ObjectInputStream ois =null;

        try{
            fis =new FileInputStream(file);
            bis =new BufferedInputStream(fis);
            ois =new ObjectInputStream(bis);

            Bike.bikeList = (ArrayList<Bike>) ois.readObject();

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
        File file = new File(fileRoot+"rentlist.txt");
        FileOutputStream fos = null;
        BufferedOutputStream bos =null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(file,true);
            bos = new BufferedOutputStream(fos);
            out = new ObjectOutputStream(bos);
            out.writeObject(RentList.rentList);
            System.out.println("대여내역이 저장되었습니다");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                out.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }

        void readRentList() {

            File file = new File(fileRoot+"rentlist.txt");
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            ObjectInputStream ois = null;

            try{
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                ois = new ObjectInputStream(bis);

                RentList.rentList = (ArrayList<RentList>) ois.readObject();

                for(Object rentList : RentList.rentList) {
                    System.out.println(rentList);
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

    void loadRentList() {

        File file = new File(fileRoot+"rentlist.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;

        try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            RentList.rentList = (ArrayList<RentList>) ois.readObject();

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

    void calculateTotalSales() {

    }
}
