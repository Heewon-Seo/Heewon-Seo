package kr.or.tashow;

public class Admin {
    private String id;
    private String pw;

    public Admin() {
        this.id = "admin";
        this.pw = "admin!";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

}
