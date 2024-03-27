package javafx01.entity;

public class Account {
    private String account;
    private String password;
    public  Account(String account1,String password2){
        this.account=account1;
        this.password=password2;

    }
    public String getaccount(){
        return this.account;
    }
    public String getPassword(){
        return this.password;
    }
}
