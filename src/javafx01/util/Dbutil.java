package javafx01.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dbutil {
    private String fileName;
    private Map<String, String> accountMap;

    public Dbutil(String fileName) {
        this.fileName = fileName;
        this.accountMap = loadAccounts();
    }

    private Map<String, String> loadAccounts() {
        Map<String, String> accountMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    accountMap.put(username, password);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load accounts: " + e.getMessage());
        }
        return accountMap;
    }

    private void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : accountMap.entrySet()) {
                String line = entry.getKey() + "," + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save accounts: " + e.getMessage());
        }
    }

    public void registerAccount(String username, String password) {
        if (accountMap.containsKey(username)) {
            System.out.println("Username already exists");
        } else {
            accountMap.put(username, password);
            saveAccounts();
            System.out.println("Registration successful");
        }
    }

    public boolean checkAccount(String username, String password) {
        return accountMap.containsKey(username) && accountMap.get(username).equals(password);
    }



}




