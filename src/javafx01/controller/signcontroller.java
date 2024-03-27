package javafx01.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx01.Main;
import javafx01.util.Dbutil;

import javafx.scene.control.TextField;


public class signcontroller {

    @FXML
    private TextField account11;

    @FXML
    private PasswordField password2;
    @FXML
    private PasswordField password3;

public void addStudent(){
        String password5=password3.getText();
        String account2=account11.getText();

    Dbutil manager = new Dbutil("accounts.txt");
    manager.registerAccount(account2, password5);


 }
 public void cheakaccount(String username,String password){
     Dbutil manager = new Dbutil("accounts.txt");
     if (manager.checkAccount(username, password)) {
         System.out.println("Login successful");
     } else {
         System.out.println("Login failed");
     }
 }
    public void back(){
        Main.changeView("view/login.fxml");

    }


}
