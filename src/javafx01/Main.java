package javafx01;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        // write your code here
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        stage.setTitle("图书馆座位管理系统");
        changeView("view/login.fxml");
        stage.show();
    }

    public static void changeView(String fxml){
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(fxml));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void addView(String fxml){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(fxml));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
