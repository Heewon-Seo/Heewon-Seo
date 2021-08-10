package kr.or.tashow;

public class Admin {
    private String id;
    private BikeType bikeType;
    private int price;

    public Admin() {
    }

    public Admin(String id, BikeType bikeType, int price) {
        this.id = id;
        this.bikeType = bikeType;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
