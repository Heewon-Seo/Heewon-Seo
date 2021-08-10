package kr.or.tashow;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import static kr.or.tashow.BikeService.bikeList;
import static kr.or.tashow.BikeService.rentList;

public class FileIO {
    private final String fileRoot;


    public FileIO() {
        fileRoot = "C:\\intelli_workspace\\UserData\\ll";
    }

    void startSystem() {
        File file = new File(fileRoot + "userlist.txt");
        File file2 = new File(fileRoot + "rentlist.txt");
        File file3 = new File(fileRoot + "bikelist.txt");

        if (!file.exists()) {
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

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            UserSystem.userList = (HashMap<String, User>) ois.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                bis.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            fis = new FileInputStream(file2);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            rentList = (ArrayList<RentList>) ois.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            fis = new FileInputStream(file3);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            bikeList = (HashMap<String, Bike>) ois.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void writeUserList() {// Filewrite > UserList
        File userList = new File(fileRoot + "userlist.txt");
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(userList);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);

            oos.writeObject(UserSystem.userList);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void readUserList() {
        File file = new File(fileRoot + "userlist.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            UserSystem.userList = (HashMap<String, User>) ois.readObject();
            TreeMap<String, User> userListSort = new TreeMap<>(UserSystem.userList);
            Iterator<String> keyiterator = userListSort.keySet().iterator();

            System.out.println("=====================비트를 타쇼 회원 리스트=====================");

            String userListKey;
            User userListValue;

            while (keyiterator.hasNext()) {
                userListKey = keyiterator.next();
                userListValue = userListSort.get(userListKey);
                System.out.println(userListValue);
            }
            if (UserSystem.userList.size() == 0) {
                System.out.println("가입된 회원이 없습니다"); // 추가
            }
            System.out.println("===============================================================");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void writeBikeList() {
        File file = new File(fileRoot + "bikelist.txt");
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);

            oos.writeObject(bikeList);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void readBikeList() {
        File file = new File(fileRoot + "bikelist.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            bikeList = (HashMap<String, Bike>) ois.readObject();
            TreeMap<String, Bike> bikeListSort = new TreeMap<>(bikeList);
            Iterator<String> keyiterator = bikeListSort.keySet().iterator();

            System.out.println("====== 비트를 타쇼 자전거 등록 리스트 ======");

            String bikeListKey;
            Bike bikeListValue;

            while (keyiterator.hasNext()) {
                bikeListKey = keyiterator.next();
                bikeListValue = bikeListSort.get(bikeListKey);
                System.out.println("[" + bikeListKey + "]" + bikeListValue);
            }

            if (bikeList.size() == 0) {
                System.out.println("등록된 자전거가 없습니다.");
            }

            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void writeRentList() {
        File file = new File(fileRoot + "rentlist.txt");
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
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

    void readRentList() {
        File file = new File(fileRoot + "rentlist.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            rentList = (ArrayList<RentList>) ois.readObject();

            for (RentList rent : rentList) {
                System.out.println(rent);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void loadRentList() {

        File file = new File(fileRoot + "rentlist.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            rentList = (ArrayList<RentList>) ois.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void loadBikeList() {
        File file = new File(fileRoot + "bikelist.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            bikeList = (HashMap<String, Bike>) ois.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
