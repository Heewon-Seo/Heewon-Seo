package kr.or.tashow;

/*
enum 을 사용한 이유.. class를 여러개 두지 않고 간결하게 코드구성
HushMap보다는 단순한 리스트 이기 때문에 ArrayList를 사용
이 클래스는 데이터를 담을 수 있는 클래스
 */


import java.util.ArrayList;

enum BikeType {
    Single,
    Twin;
}

enum RentalStatus {
    // 대여가능
    AVAILABLE,
    // 대여중,
    UNAVAILABLE;
}

public class Bike{

    private String id; // 일련번호
    private BikeType type; // 1인용, 2인용
    private RentalStatus rentalStatus; //대여가능, 대여중
    private int price; // 가격
    static ArrayList<Bike> bikeList = new ArrayList<Bike>();

    Bike () {

    }

    Bike(String id, BikeType type, RentalStatus rentalStatus, int price) {
        this.id = id;
        this.type = type;
        this.rentalStatus = rentalStatus;
        this.price = price;

    }

    @Override
    public String toString() {
        return "Bike{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", rentalStatus=" + rentalStatus +
                ", price=" + price +
                '}';
    }

    public String getId() {
        return id;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public BikeType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(BikeType type) {
        this.type = type;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
