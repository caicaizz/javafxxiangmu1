package javafx01.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx01.entity.Staff;
import javafx01.entity.Reservation;

public class Fileutil {
    private static final String DATA_FILE = "resveration.txt";

    public List<Reservation> readData(){
        List<Reservation> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))){
            String line = reader.readLine();
            while (line != null) {
                String[] dataArray = line.split(",");
                String seat=dataArray[0];
                String time=dataArray[1];
                String account=dataArray[2];
                String start=dataArray[3];
                String end=dataArray[4];
                Reservation reservation =new Reservation(seat,time,account);
                result.add(reservation);


                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void addData(Staff staff){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE, true))){
            writer.write(staff.toString());
            writer.newLine();
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
