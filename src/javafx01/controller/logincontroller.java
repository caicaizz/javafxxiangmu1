package javafx01.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx01.Main;
import javafx01.util.Dbutil;
import javafx01.util.StringUtil;

public class logincontroller {
    public static String accountInfo;


    @FXML
    private TextField account;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorinfo;

    @FXML
    public void initialize(){

    }
    @FXML
    public void doLogin(){

        String passwordText = password.getText();
        String account2=account.getText();
        //学生账号密码为八位
        //管理员为六位
        if (StringUtil.isEmpty(account2)) {
            errorinfo.setText("请输入账户！！！");
            errorinfo.setVisible(true);
            return;
        }

        if (StringUtil.isEmpty(passwordText)) {
            errorinfo.setText("请输入密码！！！");
            errorinfo.setVisible(true);
            return;
        }


            Dbutil manager = new Dbutil("accounts.txt");
            if (manager.checkAccount(account2, passwordText)) {
                if(account2.length()==8){
                    Main.changeView("view/student.fxml");
                    accountInfo=account2;
                }
                else if(account2.length()==6){
                    Main.changeView("view/admin.fxml");
                }

            }


        else {
            errorinfo.setText("账户/密码错误！！！");
            errorinfo.setVisible(true);
        }
    }
    public void dosign(){

        Main.changeView("view/signup.fxml");

    }
    public  String getaccount(){
        return account.getText();
    }


}
