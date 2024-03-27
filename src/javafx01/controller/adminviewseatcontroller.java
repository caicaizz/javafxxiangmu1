package javafx01.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx01.Main;
import javafx01.entity.Reservation;
import javafx01.util.Fileutil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class adminviewseatcontroller extends Fileutil {

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

    @FXML
    private TextField seatid;
    @FXML
    private DatePicker day;
    @FXML
    private TextField start;
    @FXML

    private TextField end;
    @FXML
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();




    public boolean isture( String seatToCheck) {
        if (seatToCheck.isEmpty()) {
            // 处理空字符串的情况
            return false;
        }
        else {

            int i = Integer.parseInt(seatToCheck);
            return i < 1001;
        }

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
                if (parts.length >= 3) { // 检查parts数组的长度是否足够长
                    String time = parts[0];
                    String seat = parts[1];
                    String account1 = parts[2];

                    Reservation reservation = new Reservation(time, seat,account1);
                    reservations1.add(reservation);
                } else {
                    // 处理数组长度不足的情况
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        table.setItems(reservations1);
    }

    public void back(){
        Main.changeView("view/admin.fxml");
    }


}
