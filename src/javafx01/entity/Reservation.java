package javafx01.entity;

public class Reservation {
    private String seat;
    private String time;
    private String account;






    public Reservation(String seat,String time,String account) {
        this.seat=seat;
        this.time=time;
        this.account=account;

    }

    public String getSeat() {
        return this.seat;
    }
    public String getTime() {
        return this.time;
    }
    public String getAccount() {
        return this.account;
    }



}
