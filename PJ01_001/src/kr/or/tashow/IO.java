package kr.or.tashow;

import java.io.*;
import java.util.*;

import static kr.or.tashow.BikeService.bikeList;
import static kr.or.tashow.BikeService.rentList;

public class IO {
    private String fileRoot;


    public IO() {
        fileRoot = "/Users/heewonseo/Documents/1stProject/lists/";
    }

    void startSystem() {
        File file = new File(fileRoot+"userlist.txt");
        File file2 = new File(fileRoot+"rentlist.txt");
        File file3 = new File(fileRoot+"bikelist.txt");

        if(!file.exists()) {
            writeUserList();
        }
        if (!file2.exists()) {
            writeRentList();
        }
        if (!file3.exists()) {
            writeBikeList();
        }

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;

        try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            UserSystem.userList = (HashMap<String, User>) ois.readObject();

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

            rentList = (ArrayList<RentList>) ois.readObject();

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

            bikeList = (HashMap<String, Bike>) ois.readObject();

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

    void writeUserList() {// Filewrite > UserList
        File userList = new File(fileRoot+"userlist.txt");
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;

        try{
            fos = new FileOutputStream(userList);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);

            oos.writeObject(UserSystem.userList);

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

            UserSystem.userList = (HashMap<String, User>) ois.readObject();

            System.out.println("=====================비트를 타쇼 회원 리스트=====================");

            for (Map.Entry<String,User> entrySet : UserSystem.userList.entrySet()) {
                System.out.println(entrySet.getValue());
            }

            System.out.println("===============================================================");

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

            oos.writeObject(bikeList);

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
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            bikeList = (HashMap<String, Bike>) ois.readObject();

            System.out.println("====== 비트를 타쇼 자전거 등록 리스트 ======");

            for (Map.Entry<String,Bike> entrySet : bikeList.entrySet()) {
                System.out.println("["+entrySet.getKey()+"]" + entrySet.getValue());
            }

            System.out.println("========================================");

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
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            out = new ObjectOutputStream(bos);
            out.writeObject(rentList);

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

    void readRentList () {
        File file =new File(fileRoot+"rentlist.txt");
        FileInputStream fis =null;
        BufferedInputStream bis =null;
        ObjectInputStream ois =null;

        try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            rentList = (ArrayList<RentList>) ois.readObject();

            for(RentList rent : rentList) {
                System.out.println(rent);
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

            rentList = (ArrayList<RentList>) ois.readObject();

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

    void loadBikeList () {
        File file =new File(fileRoot+"bikelist.txt");
        FileInputStream fis =null;
        BufferedInputStream bis =null;
        ObjectInputStream ois =null;

        try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            bikeList = (HashMap<String, Bike>) ois.readObject();

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
}
