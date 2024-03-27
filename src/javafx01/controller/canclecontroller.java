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
import javafx01.entity.Reservation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class canclecontroller {
    @FXML
    private TableView<Reservation> table;

    @FXML
    private TableColumn<Reservation,String> seat;
    @FXML
    private TableColumn<Reservation,String> account1;
    @FXML
    private TableColumn<Reservation,String> time1;
    @FXML
    private TextField cheakseatid;
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();
    @FXML
    public  void loadReservationsFromFile() {

        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        account1.setCellValueFactory(new PropertyValueFactory<>("account"));
        time1.setCellValueFactory(new PropertyValueFactory<>("time"));

        ObservableList<Reservation> reservations1 = FXCollections.observableArrayList();
        //   List<Reservation> reservation = Fileutil.readData();
        //  table.setItems(FXCollections.observableList(reservation));
        try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String time = parts[0];
                String seat = parts[1];
                String account1 = parts[2];
                String account2 = logincontroller.accountInfo;
                if(account2.equals(seat)) {

                    Reservation reservation = new Reservation(time, seat, account1);
                    reservations1.add(reservation);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        table.setItems(reservations1);




    }
    private void deleteReservationFromFile(String seatToDelete) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("reservations.txt"));
            List<String> updatedLines = new ArrayList<>();

            for (String line : lines) {
                String[] parts = line.split(",");
                String seat = parts[0];

                if (!seat.equals(seatToDelete)) {
                    updatedLines.add(line);
                }
            }

            Files.write(Paths.get("reservations.txt"), updatedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void deleteReservationFromFileButtonClicked(ActionEvent event) {
        String seatToDelete = cheakseatid.getText();
        deleteReservationFromFile(seatToDelete);
        cheakseatid.clear();
        loadReservationsFromFile();
    }
    public void back(){
        Main.changeView("view/student.fxml");
    }


}



