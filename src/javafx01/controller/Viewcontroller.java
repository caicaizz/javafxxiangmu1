package javafx01.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx01.Main;
import javafx01.entity.Account;
import javafx01.util.Fileutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Viewcontroller  extends Fileutil {

    @FXML
    private TableView<Account> table;

    @FXML
    private TableColumn<Account,String> password;
    @FXML
    private TextField account;

    @FXML
    public void loadReservationsFromFile() {

        //account.setCellValueFactory(new PropertyValueFactory<>("account"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        ObservableList<Account> reservations1 =  FXCollections.observableArrayList();;
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(",");
                    String account =  parts[0];
                    String password1 = parts[1];

                    Account reservation = new Account(account,line);
                    reservations1.add(reservation);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        table.setItems(reservations1);
    }
    private void deleteReservationFromFile(String seatToDelete) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("accounts.txt"));
            List<String> updatedLines = new ArrayList<>();

            for (String line : lines) {
                String[] parts = line.split(",");
                String seat = parts[0];

                if (!seat.equals(seatToDelete)) {
                    updatedLines.add(line);
                }
            }

            Files.write(Paths.get("accounts.txt"), updatedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void deleteReservationFromFileButtonClicked(ActionEvent event) {
        String seatToDelete = account.getText();
        deleteReservationFromFile(seatToDelete);
        account.clear();
        loadReservationsFromFile();
    }
    public void back(){
        Main.changeView("view/admin.fxml");
    }

}
