package kr.or.tashow;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BikeService implements Serializable {
    static HashMap<String, Bike> bikeList = new HashMap<>();
    static ArrayList<RentList> rentList = new ArrayList<>();
    Time time;
    IO io;
    User user;
    Scanner scan;
    int index;
    int availableSingleBikes;
    int availableTwinBikes;

    public BikeService() {
        time = new Time();
        io = new IO();
        user = new User();
        scan = new Scanner(System.in);
        index = 0;
        availableSingleBikes = 0;
        availableTwinBikes = 0;
    }

    void rentalBike(String type) { // 대여
        DateFormat df = new SimpleDateFormat("HH:mm");
        checkAvailable();
        if (bikeList.isEmpty() || (type.equals("S") && availableSingleBikes == 0)
                || (type.equals("T") && availableTwinBikes == 0)) {
            System.out.println("대여 가능한 자전거가 없습니다");
        } else {
            for (String key : bikeList.keySet()) {
                if (key.startsWith(type) && bikeList.get(key).getRentalStatus().equals(RentalStatus.AVAILABLE)) {
                    Bike bike = bikeList.get(key);
                    bike.setRentalStatus(RentalStatus.UNAVAILABLE);
                    System.out.println("====================================================");
                    System.out.println("            고객님의 자전거 번호: " + key);
                    System.out.println("            대여 시작 시각: " + df.format(time.setStartTime().getTime()));
                    System.out.println(" ** 반납 시에 자전거 번호를 입력하셔야 하니, 잘 기억해 주세요! **");
                    System.out.println("====================================================");
                    rentList.add(new RentList(key, time.setStartTime(), time.setDefaultEndTime(), Menu.cur_user_id));
                    // 기본 종료 시각을 강제로 부여 (0시0분)
                    io.writeBikeList();
                    io.writeRentList();
                    break;
                }
            }
        }
    }

    void checkAvailable() { // 대여 가능한 자전거 대수 계산
        availableSingleBikes = 0;
        availableTwinBikes = 0;
        for (Map.Entry<String, Bike> entrySet : bikeList.entrySet()) {
            BikeType type = entrySet.getValue().getType();
            RentalStatus status = entrySet.getValue().getRentalStatus();
            if (type.equals(BikeType.Single) && status.equals(RentalStatus.AVAILABLE)) {
                availableSingleBikes++;
            } else if (type.equals(BikeType.Twin) && status.equals(RentalStatus.AVAILABLE)) {
                availableTwinBikes++;
            }
        }
    }

    boolean hasBikes () {
        String bikeId = null;
        for (int i = 0 ; i < rentList.size() ; i++) {
            if (rentList.get(i).getUserPhoneNum().equals(Menu.cur_user_id)) { // 대여 내역에서 사용자가 대여한 내역 찾음
                bikeId = rentList.get(i).getId(); // 그 자전거 ID를 저장
            }
        }
        for (Map.Entry<String, Bike> entrySet : bikeList.entrySet()) { // 자전거 리스트 검색
            if (bikeId.equals(entrySet.getKey()) && entrySet.getValue().getRentalStatus().equals(RentalStatus.UNAVAILABLE)) {
                return true;
            }
        } return false;
    }

    void returnBike() { // 반납
        String id = null;
        if (!hasBikes()) { //RentList에 대여한 목록에 없다면
            System.out.println("대여 중인 자전거가 없습니다");
            return;
        }
        System.out.println("반납할 자전거의 일련번호를 입력해주세요 (예: S-1234) | 0. 이전화면");
        try {
            id = scan.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (String key : bikeList.keySet()) { // bikeList
            io.loadRentList();
            if (bikeList.containsKey(id) && bikeList.get(id).getRentalStatus().equals(RentalStatus.UNAVAILABLE)) {
                for (int i = 0; i < rentList.size(); i++) {
                    if (rentList.get(i).getId().contains(id)) {
                        RentList list = rentList.get(i);
                        // time.inputEndTime(i); > 진짜 시간
                        time.testEndTime(i); // > 테스트용 시간
                        int fee = calculateFee(id, list.getStartTime(), list.getEndTime()); // 시간입력
                        payFee(i, fee, id); // 계산
                        io.writeBikeList();
                        io.writeRentList();
                        break;
                    }
                }
                break;
            } else if (id.equals("0")) {
                System.out.println("이전화면으로 돌아갑니다");
                break;
            } else {
                System.out.println("일련번호가 일치하지 않습니다.");
                break;
            }
        }
    }

    void payFee(int index, int fee, String id) { // try catch
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("결제요금: " + df.format(fee) + "원");
        System.out.println("[요금안내] 1시간 당 1인용 : 1,000원, 2인용 : 2,000원");
        System.out.println("* 요금은 시간 단위로 계산되어 1분 초과 시부터 올림 적용됩니다");
        System.out.println("결제하시겠습니까?");
        System.out.println("1. 예 | 2. 아니오 (취소)");
        int input = 0;
        try {
            input = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        if (input == 1) {
            rentList.get(index).setFee(fee);
            bikeList.get(id).setRentalStatus(RentalStatus.AVAILABLE); // 반납처리
            io.writeRentList();
            io.writeBikeList();
            System.out.println("결제가 완료되었습니다.");
            System.out.println("이용해 주셔서 감사합니다 :D");
        } else if (input == 2) {
            rentList.get(index).setFee(0);
            Calendar defaultTime = Calendar.getInstance(); // time.setDefaultTime 쓰면 오류나서 일단 이렇게 해뒀음
            defaultTime.set(Calendar.HOUR_OF_DAY, 0);
            defaultTime.set(Calendar.MINUTE, 0);
            rentList.get(index).setEndTime(defaultTime);
            io.writeRentList();
            System.out.println("결제가 취소되었습니다");
            System.out.println("이전화면으로 돌아갑니다");
        }
    }

    int calculateFee(String bikeNum, Calendar startTime, Calendar endTime) {
        int fee = 0;
        if (bikeNum.startsWith("S")) {
            fee = time.getTime(startTime, endTime) * 1000;
        } else if (bikeNum.startsWith("T")) {
            fee = time.getTime(startTime, endTime) * 2000;
        }
        return fee;
    }

    void calculateTotalSales() {
        DecimalFormat df = new DecimalFormat("#,###");
        io.loadRentList();
        int fee;
        int totalSales = 0;
        for (RentList list : rentList) {
            fee = list.getFee();
            totalSales += fee;
        }
        System.out.println("=========== 현재 기준 총 매출액 ============");
        System.out.println(df.format(totalSales) + "원\n");
    }

    /*
    void showMyBikes() {
        io.loadRentList();
        io.loadBikeList();
        System.out.println("=========현재 대여 중인 자전거=========");
        System.out.println("회원ID: " + Menu.cur_user_id);
        for(RentList list : rentList) {
            if(list.getUserPhoneNum().equals(Menu.cur_user_id) && bikeList.get(list.getId()).getRentalStatus().equals(RentalStatus.UNAVAILABLE)) {
                System.out.println("자전거ID: " + list.getId() + " | 대여시각: " + list.getStartTime().get(Calendar.HOUR_OF_DAY)+"시 "+ list.getStartTime().get(Calendar.MINUTE)+"분");
            }
        }
    }
     */
}
