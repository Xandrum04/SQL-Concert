package com.mycompany.gui;
import java.sql.*;

public class PROJECT {
private static final String DB_URL = "jdbc:mysql://localhost:3306/recordcompany";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        
}


// Method to fetch and print data from the database
    public static void DisplayTable(String tableName) {
        String query = "SELECT * FROM " + tableName;
        try (
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            GUI.jTextArea2.setText("");
            GUI.jTextArea2.append(GUI.tableName.toUpperCase() +"\n\n");
            int numberOfColumns = metaData.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++)
                 GUI.jTextArea2.append(String.format("%-15s\t", metaData.getColumnName(i)));

            GUI.jTextArea2.append("\n");

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    GUI.jTextArea2.append(String.format("%-15s\t", resultSet.getObject(i)));
                GUI.jTextArea2.append("\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            GUI.jTextArea2.append("Error fetching data: " + ex.getMessage());
        }
    }
    
    
    // Μέθοδος για εισαγωγή δεδομένων
    public static void InsertData(String input) {
        String query = input;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = con.createStatement()) {
            
            int rowsInserted = stmt.executeUpdate(query);
            GUI.jTextArea2.setText(rowsInserted + " row(s) inserted successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            GUI.jTextArea2.append("Error inserting data: " + ex.getMessage());
        }
    }

    // Μέθοδος για τροποποίηση δεδομένων
    public static void UpdateData(String input) {
        String query = input;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = con.createStatement()) {

            int rowsUpdated = stmt.executeUpdate(query);
            GUI.jTextArea2.setText(rowsUpdated + " row(s) updated successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            GUI.jTextArea2.append("Error updating data: " + ex.getMessage());
        }
    }

    // Μέθοδος για διαγραφή δεδομένων
    public static void DeleteData(String input) {
        String query = input;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = con.createStatement()) {

            int rowsDeleted = stmt.executeUpdate(query);
            GUI.jTextArea2.setText(rowsDeleted + " row(s) deleted successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            GUI.jTextArea2.append("Error deleting data: " + ex.getMessage());
        }
    }
}

    
