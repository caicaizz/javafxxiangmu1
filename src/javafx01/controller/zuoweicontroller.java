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


public class zuoweicontroller extends Fileutil {


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



@FXML
    public void checkSeatButtonClicked(ActionEvent event) {
        String seatToCheck = cheakseatid.getText();
    String timeToCheck = day.getValue().toString();
        boolean iscuizai= isture(seatToCheck);

        if(iscuizai){

                if(isSeatReserved1(seatToCheck,timeToCheck)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("座位预约系统");
                    alert.setHeaderText("座位已被预约");
                    alert.setContentText("座位 " + seatToCheck + " 已被预约");
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("座位预约系统");
                    alert.setHeaderText("座位未被预约");
                    alert.setContentText("座位 " + seatToCheck + " 未被预约");
                    alert.showAndWait();
                }




      }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("座位预约系统");
            alert.setHeaderText("座位不存在");
            alert.setContentText("座位 " + seatToCheck + " 不存在");
            alert.showAndWait();
        }
    }

    public boolean isture( String seatToCheck) {
        if (seatToCheck.isEmpty()) {
            // 处理空字符串的情况
            return false;
        }
        else {

            int i = Integer.parseInt(seatToCheck);
            return i < 191;
        }

    }


/*    private boolean isSeatReserved1(String seatToCheck,String timeyuyue) {
        try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String time = parts[0];//座位号
                    String seat = parts[1];//账号
                    String account1 = parts[2];
                    String day[]=account1.split(" ");
                    String[] hours = day[1].split("~");

                    int startHour = Integer.parseInt(hours[0].split(":")[0]);
                    int endHour = Integer.parseInt(hours[1].split(":")[0]);

                    int startHour1 = Integer.parseInt(start.getText());
                    int end1 = Integer.parseInt(end.getText());

                    if (seatToCheck.equals(time)) {
                        if(timeyuyue.equals(day[0])){
                        if(((startHour1<endHour)&&(startHour1>startHour))||((end1>startHour)&&(end1<endHour))){
                            return true;
                        }
                        else {
                            return false;
                        }
                        }


                      else {
                            return false;

                        }

                    }
                } else {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // 添加这行代码以确保在循环结束后有返回值
    }*/
private boolean isSeatReserved1(String seatToCheck, String timeyuyue) {
    boolean isreserve=false;
    try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                String time = parts[0]; // 座位号
                String seat = parts[1]; // 账号
                String account1 = parts[2];
                String day[] = account1.split(" ");
                String[] hours = day[1].split("~");

                int startHour = Integer.parseInt(hours[0].split(":")[0]);
                int endHour = Integer.parseInt(hours[1].split(":")[0]);

                int startHour1 = Integer.parseInt(start.getText());
                int end1 = Integer.parseInt(end.getText());


                if (seatToCheck.equals(time)) {
                    if (timeyuyue.equals(day[0])) {
                        if (end1 <= startHour || startHour1 >= endHour) {
                            // 预约时间与已有预约时间不冲突
                          isreserve=false;
                        } else {
                            // 预约时间与已有预约时间冲突
                          isreserve=true;
                          break;
                        }
                    } else {
                        // 座位已被预约，但日期不匹配
                       isreserve= false;
                    }
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return isreserve; // 座位未被预约
}



    @FXML
    public void reserveButtonClicked(ActionEvent event) {
        String seatToCheck = seatid.getText();

        String account1 = logincontroller.accountInfo;

        String timeToCheck = day.getValue().toString();
        boolean iscuizai= isture(seatToCheck);

        if(iscuizai){

            if(isSeatReserved1(seatToCheck,timeToCheck)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("座位预约系统");
                alert.setHeaderText("座位已被预约");
                alert.setContentText("座位 " + seatToCheck + " 已被预约");
                alert.showAndWait();
            }
            else {
                Reservation reservation = new Reservation(seatToCheck,account1,timeToCheck);
                reservations.add(reservation);

                table.setItems(reservations);
                seatid.clear();
                String account2 = logincontroller.accountInfo;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt", true))) {

                    writer.write(reservation.getSeat() + "," +account2 + "," + reservation.getAccount()+" "+start.getText()+":00"+"~"+end.getText()+":00");
                    writer.newLine();
                    seatid.clear();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("座位预约系统");
                alert.setHeaderText("座位预约成功");

                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("座位预约系统");
            alert.setHeaderText("座位不存在");
            alert.setContentText("座位 " + seatToCheck + " 不存在");
            alert.showAndWait();
        }

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
                    String account2 = logincontroller.accountInfo;
                    if(account2.equals(seat)) {
                        Reservation reservation = new Reservation(time, seat, account1);
                        reservations1.add(reservation);
                    }
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
    public void saveReservationsToFile() {
        String seatToCheck = seatid.getText();
        String YuyueTime=day.getValue().toString();
        String starttime = start.getText();
        String endtime=end.getText();

        boolean isReserved = isSeatReserved1(seatToCheck,YuyueTime);
        if (isture(seatToCheck)) {
            if (isReserved) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("座位预约系统");
                alert.setHeaderText("座位已被预约");
                alert.setContentText("座位 " + seatToCheck + " 已被预约");
                alert.showAndWait();

            } else {
                String account1 = logincontroller.accountInfo;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt", true))) {
                    for (Reservation reservation : reservations) {
                        writer.write(reservation.getSeat() + "," +account1 + "," + reservation.getAccount()+" "+start.getText()+":00"+"~"+end.getText()+":00");
                        writer.newLine();

                        seatid.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("座位预约系统");
            alert.setHeaderText("座位不存在");
            alert.setContentText("座位 " + seatToCheck + " 不存在");
            alert.showAndWait();
        }

    }
    @FXML
    public void back(){
        Main.changeView("view/student.fxml");
    }
    @FXML
    public void back1(){
        Main.changeView("view/admin.fxml");
    }

}
