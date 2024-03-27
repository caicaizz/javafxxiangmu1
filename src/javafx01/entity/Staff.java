package javafx01.entity;

public class Staff {
    private String account1;

    private String password4;
    public Staff(String account,String password4){
        this.account1=account;
        this.password4=password4;
    }
    public String getaccount(){
        return this.account1;
    }
    public  String getPassword4(){
        return this.password4;
    }

}
